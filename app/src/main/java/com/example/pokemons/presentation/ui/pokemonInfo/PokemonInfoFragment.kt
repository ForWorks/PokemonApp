package com.example.pokemons.presentation.ui.pokemonInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.pokemons.PokemonInfoViewModel
import com.example.pokemons.R
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonInfoFragment : Fragment() {

    private val pokemonInfoViewModel: PokemonInfoViewModel by viewModels()
    private val binding by lazy { FragmentPokemonListBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}