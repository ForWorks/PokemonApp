package com.example.pokemons.domain.model

data class UIPokemonList (
    val count: Int,
    val results: List<UIResults>,
)

data class UIResults (
    val name: String,
    val url: String,
)