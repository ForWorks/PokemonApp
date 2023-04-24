package com.example.pokemons.presentation.ui.pokemonList.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pokemons.data.model.Pokemon

interface PokemonListInterface {
    fun fetchNext()
    //fun clearError()
}