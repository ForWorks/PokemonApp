package com.example.pokemons.presentation.ui.pokemonList.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemons.data.repository.PokemonRepositoryImpl
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.core.paging.PokemonPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl) : ViewModel() {

    //val isLoading = MutableLiveData(false)

    val list = Pager(PagingConfig(1)) {
        PokemonPagingSource(pokemonRepository)
    }.flow.cachedIn(viewModelScope)

    val pokemonList = mutableListOf<UIPokemon?>()
    val pokemonLiveData = MutableLiveData<List<UIPokemon?>>()

    fun getPokemonList(offset: Int, limit: Int) {
        try {
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
        catch (e: ConnectException) {
            println(e)
        }

    }
}