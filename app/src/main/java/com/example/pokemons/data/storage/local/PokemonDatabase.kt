package com.example.pokemons.data.storage.local

import android.content.Context
import androidx.room.*
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.Sprites
import com.example.pokemons.data.model.Type
import com.example.pokemons.data.model.Types

@TypeConverters(Converter::class)
@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun getPokemonDAO(): PokemonDAO

    companion object{
        private var instance: PokemonDatabase? = null
        fun getInstance(context: Context): PokemonDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context.applicationContext, PokemonDatabase::class.java, DATABASE_NAME)
                    .build()
            }
            return instance!!
        }
        private const val DATABASE_NAME = "pokemon"
    }
}

class Converter {

    @TypeConverter
    fun spritesToString(sprites: Sprites) : String {
        return sprites.backDefault + DELIMITER +
               sprites.backFemale +  DELIMITER +
               sprites.backShiny + DELIMITER +
               sprites.backShinyFemale + DELIMITER +
               sprites.frontDefault + DELIMITER +
               sprites.frontFemale + DELIMITER +
               sprites.frontShiny + DELIMITER +
               sprites.frontShinyFemale
    }

    @TypeConverter
    fun stringToSprites(sprites: String) : Sprites {
        val spriteList = sprites.split(DELIMITER)
        return Sprites(
            backDefault = spriteList[0],
            backFemale = spriteList[1],
            backShiny = spriteList[2],
            backShinyFemale = spriteList[3],
            frontDefault = spriteList[4],
            frontFemale = spriteList[5],
            frontShiny = spriteList[6],
            frontShinyFemale = spriteList[7],
        )
    }

    @TypeConverter
    fun typesToString(types: List<Types>) : String {
        var string = EMPTY_STRING
        types.forEach {
            string += it.slot.toString() + DELIMITER +
                      it.type.name + DELIMITER +
                      it.type.url + DELIMITER
        }
        return string
    }

    @TypeConverter
    fun stringToTypes(types: String) : List<Types> {
        val typeList = types.split(DELIMITER).dropLast(1)
        val list = mutableListOf<Types>()
        var i = 0
        while(i < typeList.size) {
            list.add(
                Types(
                    slot = typeList[i++].toInt(),
                    type = Type(
                        name = typeList[i++],
                        url = typeList[i++],
                    )
                )
            )
        }
        return list
    }

    private companion object {
        const val DELIMITER = "|"
        const val EMPTY_STRING = ""
    }
}