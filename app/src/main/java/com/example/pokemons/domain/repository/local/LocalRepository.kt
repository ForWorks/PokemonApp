package com.example.pokemons.domain.repository.local

import com.example.pokemons.data.model.Pokemon

interface LocalRepository {
    suspend fun getPokemonList(offset: Int): MutableList<Pokemon>
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)
}