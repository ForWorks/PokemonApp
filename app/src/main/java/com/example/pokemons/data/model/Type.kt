package com.example.pokemons.data.model

import com.google.gson.annotations.SerializedName

data class Type (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String,
)