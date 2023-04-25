package com.example.pokemons.presentation.ui.pokemonList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.pokemons.data.network.CheckConnection
import com.example.pokemons.databinding.FragmentPokemonListBinding
import com.example.pokemons.presentation.ui.pokemonList.adapter.PokemonAdapter
import com.example.pokemons.presentation.ui.pokemonList.adapter.StateAdapter
import com.example.pokemons.presentation.ui.pokemonList.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()
    private val checkConnection by lazy { CheckConnection(requireActivity().application) }
    private val binding by lazy { FragmentPokemonListBinding.inflate(layoutInflater) }
    private companion object {
        lateinit var pokemonAdapter: PokemonAdapter
        var isInitializedAdapter = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)

        if(!isInitializedAdapter) {
            pokemonAdapter = PokemonAdapter {
                val action = PokemonListFragmentDirections.listFragmentToInfoFragment(it)
                findNavController().navigate(action)
            }
            isInitializedAdapter = true
        }

        binding.pokemonList.apply {
            adapter = pokemonAdapter
            adapter = pokemonAdapter.withLoadStateFooter(
                StateAdapter { pokemonAdapter.retry() }
            )
        }

        checkConnection.observe(viewLifecycleOwner) {
            if(pokemonAdapter.itemCount == 0) {
                if(it) {
                    CoroutineScope(Dispatchers.IO).launch {
                        pokemonListViewModel.list.collect { pagingData ->
                            pokemonAdapter.submitData(pagingData)
                        }
                    }
                }
                else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            pokemonAdapter.loadStateFlow.collect { states ->
                val state = states.refresh
                binding.progressBar.isVisible = state is LoadState.Loading
            }
        }
    }
}