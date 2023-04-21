package com.example.pokemons.data.repository

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.PokemonList
import com.example.pokemons.data.service.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonService: PokemonService) {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonList? {
        return pokemonService.getPokemonList(offset, limit).body()
    }

    suspend fun getPokemonById(id: Int): Pokemon? {
        return pokemonService.getPokemonDetails(id).body()
    }
}