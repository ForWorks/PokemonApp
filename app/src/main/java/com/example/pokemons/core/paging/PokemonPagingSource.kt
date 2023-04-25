package com.example.pokemons.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.local.LocalRepositoryImpl
import com.example.pokemons.data.repository.remote.RemoteRepositoryImpl
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.domain.utils.Constants.PAGE_SIZE
import retrofit2.HttpException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val localRepository: LocalRepositoryImpl,
    private val remoteRepository: RemoteRepositoryImpl,
) : PagingSource<Int, UIPokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UIPokemon> {
        val offset = params.key ?: 0
        val localPokemonList = localRepository.getPokemonList(offset)
        if(localPokemonList.isEmpty()) {
            return try {
                val pokemonUrls = remoteRepository.getPokemonList(offset)?.results
                pokemonUrls?.forEach {
                    val id = it.url.split(DELIMITER).dropLast(1).last().toInt()
                    val pokemon = remoteRepository.getPokemonById(id)
                    pokemon?.let { localPokemonList.add(pokemon) }
                }
                localRepository.insertPokemonList(localPokemonList)
                getLoadResult(localPokemonList, offset)
            }
            catch (exception: Exception) { LoadResult.Error(exception) }
            catch (httpException: HttpException) { LoadResult.Error(httpException) }
        }
        return getLoadResult(localPokemonList, offset)
    }

    private fun getLoadResult(pokemonList: List<Pokemon>, offset: Int): LoadResult.Page<Int, UIPokemon> {
        return LoadResult.Page(
            data = pokemonList.map { it.transform() },
            prevKey = if (offset == 0) null else offset - PAGE_SIZE,
            nextKey = if(pokemonList.size == PAGE_SIZE) offset + PAGE_SIZE else null,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, UIPokemon>): Int? { return null }

    private companion object {
        const val DELIMITER = "/"
    }
}