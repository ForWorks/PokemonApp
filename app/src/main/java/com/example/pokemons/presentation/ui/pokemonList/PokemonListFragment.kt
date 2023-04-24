package com.example.pokemons.presentation.ui.pokemonList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pokemons.data.network.CheckConnection
import com.example.pokemons.databinding.FragmentPokemonListBinding
import com.example.pokemons.presentation.ui.pokemonList.adapter.PokemonAdapter
import com.example.pokemons.presentation.ui.pokemonList.adapter.StateAdapter
import com.example.pokemons.presentation.ui.pokemonList.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.http2.ConnectionShutdownException
import java.net.ConnectException

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()
    private val checkConnection by lazy { CheckConnection(requireActivity().application) }
    private val binding by lazy { FragmentPokemonListBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonAdapter = PokemonAdapter()
        binding.pokemonList.adapter = pokemonAdapter

        checkConnection.observe(viewLifecycleOwner) {
            if(it) {
                try {
                    pokemonListViewModel.getPokemonList(0, 20)
                }
                catch (_: ConnectException) {}

            }
        }

        binding.pokemonList.adapter = pokemonAdapter.withLoadStateFooter(
            StateAdapter { pokemonAdapter.retry() }
        )

        lifecycleScope.launchWhenCreated {
            lifecycleScope.launchWhenCreated {
                pokemonListViewModel.list.collect { pagingData ->
                    pokemonAdapter.submitData(pagingData)
                }
            }
            pokemonAdapter.loadStateFlow.collect {
                val state = it.refresh
                binding.progressBar.isVisible = state is LoadState.Loading
            }
        }
    }
}