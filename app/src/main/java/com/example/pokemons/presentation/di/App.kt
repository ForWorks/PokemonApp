package com.example.pokemons.presentation.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class App : Application() {

    companion object{
        private lateinit var context: WeakReference<Context>
        fun getContext(): Context? { return context.get() }
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(this)
    }
}