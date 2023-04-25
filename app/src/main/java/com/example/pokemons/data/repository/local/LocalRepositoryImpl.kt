package com.example.pokemons.data.repository.local

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.storage.local.PokemonDAO
import com.example.pokemons.domain.repository.local.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val pokemonDAO: PokemonDAO): LocalRepository {

    override suspend fun getPokemonList(offset: Int): MutableList<Pokemon> {
        return pokemonDAO.getPokemonList(offset)
    }

    override suspend fun insertPokemonList(pokemonList: List<Pokemon>) {
        return pokemonDAO.insertPokemonList(pokemonList)
    }
}