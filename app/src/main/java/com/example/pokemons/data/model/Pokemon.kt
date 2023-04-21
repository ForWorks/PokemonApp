package com.example.pokemons.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
    @SerializedName("height") val height : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("sprites") val sprites : Sprites,
    @SerializedName("types") val types : List<Types>,
    @SerializedName("weight") val weight : Int,
)