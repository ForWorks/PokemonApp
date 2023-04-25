package com.example.pokemons.presentation.di.modules

import com.example.pokemons.data.storage.local.PokemonDAO
import com.example.pokemons.data.storage.local.PokemonDatabase
import com.example.pokemons.presentation.di.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(): PokemonDatabase {
        return PokemonDatabase.getInstance(App.getContext()!!)
    }

    @Provides
    @Singleton
    fun provideDAO(database: PokemonDatabase): PokemonDAO {
        return database.getPokemonDAO()
    }
}