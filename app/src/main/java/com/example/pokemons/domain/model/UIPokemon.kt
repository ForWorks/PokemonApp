package com.example.pokemons.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIPokemon (
   val height: Int,
   val name: String,
   val sprites: String,
   val types: List<UITypes>,
   val weight: Int,
) : Parcelable

@Parcelize
data class UITypes (
    val slot: Int,
    val type: UIType,
): Parcelable

@Parcelize
data class UIType (
    val name: String,
    val url: String,
): Parcelable