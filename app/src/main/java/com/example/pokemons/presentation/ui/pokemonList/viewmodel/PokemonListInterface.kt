package com.example.pokemons.presentation.ui.pokemonList.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pokemons.data.model.Pokemon

interface PokemonListInterface {
    fun observePokemonList(owner: LifecycleOwner, observer: Observer<List<Pokemon>>)
    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>)
    fun observeError(owner: LifecycleOwner, observer: Observer<String?>)
    //fun errorExists(): Boolean
    fun fetchNext()
    //fun clearError()
}