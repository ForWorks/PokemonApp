package com.example.pokemons.data.model

import com.example.pokemons.core.transformation.PokemonListTransformable
import com.example.pokemons.domain.model.UIPokemonList
import com.example.pokemons.domain.model.UIResults

data class PokemonList (
    val count: Int,
    val results: List<Results>,
): PokemonListTransformable<UIPokemonList> {
    override fun transform(): UIPokemonList {
        return UIPokemonList(
            count = this.count,
            results = this.results.map {
                UIResults(
                    name = it.name,
                    url = it.url,
                )
            },
        )
    }
}

data class Results (
    val name: String,
    val url: String,
)