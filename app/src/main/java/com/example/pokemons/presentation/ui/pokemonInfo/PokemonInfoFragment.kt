package com.example.pokemons.presentation.ui.pokemonInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonInfoFragment : Fragment() {

    private val binding by lazy { FragmentPokemonInfoBinding.inflate(layoutInflater) }
    private val args: PokemonInfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val pokemon = args.pokemon

        binding.apply {
            name.text = pokemon.name
            height.text = pokemon.height.toString().plus(CM)
            weight.text = pokemon.weight.toString().plus(KG)
            Glide.with(image)
                .load(pokemon.sprites)
                .error(R.drawable.ic_launcher_foreground)
                .into(image)
            var string = EMPTY_STRING
            pokemon.types.forEach {
                string += String.format(CUSTOM_TYPE, it.type.name)
            }
            types.text = string
        }
    }

    private companion object {
        const val EMPTY_STRING = ""
        const val CM = " cm"
        const val KG = " kg"
        const val CUSTOM_TYPE = "[%s] "
    }
}