package com.example.pokemons.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.storage.remote.repository.PokemonRepository
import retrofit2.HttpException

class PokemonPagingSource(private val pokemonRepository: PokemonRepository) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val offset = params.key ?: 0
            val pokemonUrls = pokemonRepository.getPokemonList(offset, 20)?.results
            val pokemonList = mutableListOf<Pokemon>()
            val responseData = mutableListOf<Pokemon>()
            pokemonUrls?.forEach {
                val id = it.url.split("/").dropLast(1).last().toInt()
                val pokemon = pokemonRepository.getPokemonById(id)
                pokemon?.let { pokemonList.add(pokemon) }
            }
            responseData.addAll(pokemonList)

            LoadResult.Page(
                data = responseData,
                prevKey = if(offset == 0) null else -20,
                nextKey = offset.plus(20),
            )
        }
        catch (exception: Exception) { LoadResult.Error(exception) }
        catch (httpException: HttpException) { LoadResult.Error(httpException) }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? { return null }
}