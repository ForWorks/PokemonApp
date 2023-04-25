package com.example.pokemons.data.storage.local

import androidx.room.*
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.domain.utils.Constants.PAGE_SIZE

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon LIMIT :limit OFFSET :offset")
    suspend fun getPokemonList(offset: Int, limit: Int = PAGE_SIZE): MutableList<Pokemon>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)
}