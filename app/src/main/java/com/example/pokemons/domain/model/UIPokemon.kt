package com.example.pokemons.domain.model

data class UIPokemon (
   val height: Int,
   val name: String,
   val sprites: String,
   val types: List<UITypes>,
   val weight: Int,
)

data class UITypes (
    val slot: Int,
    val type: UIType,
)

data class UIType (
    val name: String,
    val url: String,
)