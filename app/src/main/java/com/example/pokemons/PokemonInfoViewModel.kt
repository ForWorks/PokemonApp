package com.example.pokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.storage.remote.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {

    private val pokemonList = mutableListOf<Pokemon?>()
    val pokemonLiveData = MutableLiveData<List<Pokemon?>>()

    fun getPokemonList(offset: Int, limit: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val page = pokemonRepository.getPokemonList(offset, limit)
            page?.results?.forEach { result ->
                val id = result.url.split("/").dropLast(1).last().toInt()
                val pokemon = pokemonRepository.getPokemonById(id)
                pokemon?.let { pokemonList.add(pokemon) }                
            }
            pokemonList.forEach { println(it) }
            pokemonLiveData.postValue(pokemonList)
        }
    }
}