package com.example.pokemons.presentation.ui.list

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
import com.example.pokemons.databinding.FragmentListBinding
import com.example.pokemons.domain.utils.Constants.PAGE_SIZE
import com.example.pokemons.presentation.ui.list.adapter.PokemonAdapter
import com.example.pokemons.presentation.ui.list.adapter.StateAdapter
import com.example.pokemons.presentation.ui.list.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()
    private val checkConnection by lazy { CheckConnection(requireActivity().application) }
    private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
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
                val action = ListFragmentDirections.listFragmentToInfoFragment(it)
                findNavController().navigate(action)
            }
            lifecycleScope.launchWhenCreated {
                collectPokemonList()
                pokemonAdapter.loadStateFlow.collect { states ->
                    val state = states.refresh
                    binding.progressBar.isVisible = state is LoadState.Error
                }
            }
            isInitializedAdapter = true
        }

        binding.pokemonList.adapter =
            pokemonAdapter.withLoadStateFooter(StateAdapter { pokemonAdapter.retry() })

        checkConnection.observe(viewLifecycleOwner) { isConnect ->
            if(pokemonAdapter.itemCount < PAGE_SIZE) {
                if(isConnect) { collectPokemonList() }
            }
        }
    }

    private fun collectPokemonList() {
        CoroutineScope(Dispatchers.IO).launch {
            listViewModel.pokemonList.collect { pagingData ->
                pokemonAdapter.submitData(pagingData)
            }
        }
    }
}