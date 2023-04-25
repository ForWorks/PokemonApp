package com.example.pokemons.presentation.ui.pokemonList.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokemons.data.repository.remote.RemoteRepositoryImpl
import com.example.pokemons.core.paging.PokemonPagingSource
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.local.LocalRepositoryImpl
import com.example.pokemons.domain.model.UIPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val localRepository: LocalRepositoryImpl,
    private val remoteRepository: RemoteRepositoryImpl,
) : ViewModel() {

    val list = Pager(PagingConfig(1)) {
        PokemonPagingSource(localRepository, remoteRepository)
    }.flow

//    suspend fun getData(): List<UIPokemon> {
//        return localRepositoryImpl.getPokemonList(0).map { it.transform() }
//    }
}