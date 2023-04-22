package com.example.pokemons.presentation.ui.pokemonList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemons.PokemonInfoViewModel
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonListBinding
import com.example.pokemons.presentation.ui.pokemonList.adapter.PokemonAdapter
import com.example.pokemons.presentation.ui.pokemonList.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()
    private val binding by lazy { FragmentPokemonListBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pokemonInfoViewModel.getPokemonList(0, 20)

//        pokemonInfoViewModel.pokemonLiveData.observe(viewLifecycleOwner) {
//            binding.pokemonList.adapter = PokemonAdapter(it)
//        }

        val adapter = PokemonAdapter()

        binding.apply {
            lifecycleScope.launchWhenCreated {
                pokemonListViewModel.list.collect {
                    adapter.submitData(it)
                }
            }
            pokemonList.adapter = adapter
        }
    }
}