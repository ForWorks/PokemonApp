package com.example.pokemons.data.repository

import com.example.pokemons.data.storage.remote.service.PokemonService
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.domain.model.UIPokemonList
import com.example.pokemons.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonService: PokemonService): PokemonRepository {
    override suspend fun getPokemonList(offset: Int, limit: Int): UIPokemonList? {
        return pokemonService.getPokemonList(offset, limit).body()?.transform()
    }

    override suspend fun getPokemonById(id: Int): UIPokemon? {
        return pokemonService.getPokemonDetails(id).body()?.transform()
    }
}