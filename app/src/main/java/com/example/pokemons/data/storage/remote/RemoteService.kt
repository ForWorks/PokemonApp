package com.example.pokemons.data.storage.remote

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.PokemonList
import com.example.pokemons.domain.utils.Constants.PAGE_SIZE
import com.example.pokemons.domain.utils.Constants.POKEMON_END_POINT
import com.example.pokemons.domain.utils.Constants.POKEMON_LIST_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {
    @GET(POKEMON_END_POINT)
    suspend fun getPokemonDetails(@Path(ID) id: Int): Response<Pokemon>
    @GET(POKEMON_LIST_END_POINT)
    suspend fun getPokemonList(@Query(OFFSET) offset: Int, @Query(LIMIT) limit: Int = PAGE_SIZE): Response<PokemonList>

    private companion object {
        const val ID = "id"
        const val OFFSET = "offset"
        const val LIMIT = "limit"
    }
}