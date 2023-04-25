package com.example.pokemons.data.repository.remote

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.PokemonList
import com.example.pokemons.data.storage.remote.RemoteService
import com.example.pokemons.domain.repository.remote.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val remoteService: RemoteService): RemoteRepository {

    override suspend fun getPokemonList(offset: Int): PokemonList? {
        return remoteService.getPokemonList(offset).body()
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return remoteService.getPokemonDetails(id).body()
    }
}