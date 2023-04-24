package com.example.pokemons.domain.repository

import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.domain.model.UIPokemonList

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): UIPokemonList?
    suspend fun getPokemonById(id: Int): UIPokemon?
}