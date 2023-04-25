package com.example.pokemons.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.local.LocalRepositoryImpl
import com.example.pokemons.data.repository.remote.RemoteRepositoryImpl
import com.example.pokemons.domain.model.UIPokemon
import retrofit2.HttpException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val localRepository: LocalRepositoryImpl,
    private val remoteRepository: RemoteRepositoryImpl,
) : PagingSource<Int, UIPokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UIPokemon> {
      //  return try {
            val offset = params.key ?: 0
            val a = localRepository.getPokemonList(offset)
            val pokemonList = mutableListOf<Pokemon>()
            if(a.isEmpty()) {
                val pokemonUrls = remoteRepository.getPokemonList(offset)?.results
                pokemonUrls?.forEach {
                    val id = it.url.split(DELIMITER).dropLast(1).last().toInt()
                    val pokemon = remoteRepository.getPokemonById(id)
                    pokemon?.let { pokemonList.add(pokemon) }
                }
                localRepository.insertPokemonList(pokemonList)
            }
            else {
                pokemonList.addAll(a)
            }
            return LoadResult.Page(
                data = pokemonList.map { it.transform() },
                prevKey = if(offset == 0) null else -20,
                nextKey = offset.plus(20),
            )
       // }
        //catch (exception: Exception) { LoadResult.Error(exception) }
       // catch (httpException: HttpException) { LoadResult.Error(httpException) }
    }

    override fun getRefreshKey(state: PagingState<Int, UIPokemon>): Int? { return null }

    private companion object {
        const val DELIMITER = "/"
    }
}