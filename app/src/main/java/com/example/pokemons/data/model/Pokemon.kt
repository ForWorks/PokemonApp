package com.example.pokemons.data.model

import com.example.pokemons.core.transformation.PokemonTransformable
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.domain.model.UIType
import com.example.pokemons.domain.model.UITypes
import com.google.gson.annotations.SerializedName

data class Pokemon (
   val height: Int,
   val id: Int,
   val name: String,
   val sprites: Sprites,
   val types: List<Types>,
   val weight: Int,
): PokemonTransformable<UIPokemon> {
    override fun transform(): UIPokemon {
        return UIPokemon(
            height = this.height,
            name = this.name,
            sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${this.id}.png",
            types = this.types.map {
                UITypes(
                    slot = it.slot,
                    type = UIType(
                        name = it.type.name,
                        url = it.type.url,
                    ),
                )
            },
            weight = weight,
        )
    }
}

data class Types (
    val slot: Int,
    val type: Type,
)

data class Type (
    val name: String,
    val url: String,
)

data class Sprites (
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("back_female") val backFemale: String,
    @SerializedName("back_shiny") val backShiny: String,
    @SerializedName("back_shiny_female") val backShinyFemale: String,
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_female") val frontFemale: String,
    @SerializedName("front_shiny") val frontShiny: String,
    @SerializedName("front_shiny_female") val frontShinyFemale: String,
)