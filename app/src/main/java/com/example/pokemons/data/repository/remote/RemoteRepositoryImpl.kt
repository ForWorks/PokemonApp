package com.example.pokemons.data.repository.remote

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.PokemonList
import com.example.pokemons.data.storage.remote.RemoteService
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.domain.model.UIPokemonList
import com.example.pokemons.domain.repository.remote.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val pokemonService: RemoteService): RemoteRepository {

    override suspend fun getPokemonList(offset: Int): PokemonList? {
        return pokemonService.getPokemonList(offset).body()
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return pokemonService.getPokemonDetails(id).body()
    }
}