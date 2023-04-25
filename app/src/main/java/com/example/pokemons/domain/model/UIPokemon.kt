package com.example.pokemons.domain.model

import java.io.Serializable

data class UIPokemon (
   val height: Int,
   val name: String,
   val sprites: String,
   val types: List<UITypes>,
   val weight: Int,
): Serializable

data class UITypes (
    val slot: Int,
    val type: UIType,
): Serializable

data class UIType (
    val name: String,
    val url: String,
): Serializable