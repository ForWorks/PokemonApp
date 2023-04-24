package com.example.pokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.PokemonRepositoryImpl
import com.example.pokemons.domain.model.UIPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl): ViewModel() {


}