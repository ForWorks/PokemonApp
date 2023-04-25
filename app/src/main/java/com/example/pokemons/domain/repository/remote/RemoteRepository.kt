package com.example.pokemons.domain.repository.remote

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.PokemonList
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.domain.model.UIPokemonList

interface RemoteRepository {
    suspend fun getPokemonList(offset: Int): PokemonList?
    suspend fun getPokemonById(id: Int): Pokemon?
}