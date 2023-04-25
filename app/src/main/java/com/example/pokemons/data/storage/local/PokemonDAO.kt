package com.example.pokemons.data.storage.local

import androidx.room.*
import com.example.pokemons.data.model.Pokemon

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon LIMIT :limit OFFSET :offset")
    suspend fun getPokemonList(offset: Int, limit: Int = 20): MutableList<Pokemon>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)
}