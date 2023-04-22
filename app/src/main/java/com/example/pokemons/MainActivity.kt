package com.example.pokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.pokemons.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonInfoViewModel: PokemonInfoViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pokemonInfoViewModel.getPokemonList(0, 20)

//        pokemonInfoViewModel.pokemonLiveData.observe(this) {
//            println(it)
//        }
    }
}