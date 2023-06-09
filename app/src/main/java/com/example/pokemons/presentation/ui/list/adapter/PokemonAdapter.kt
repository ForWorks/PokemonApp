package com.example.pokemons.presentation.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.databinding.PokemonItemBinding
import com.example.pokemons.domain.model.UIPokemon
import com.example.pokemons.presentation.ui.list.ListFragmentDirections
import javax.inject.Inject

class PokemonAdapter @Inject constructor():
    PagingDataAdapter<UIPokemon, PokemonAdapter.ViewHolder>(PokemonDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val pokemon = getItem(position)
            pokemon?.let {
                pokemonTitle.text = pokemon.name
                root.setOnClickListener {
                    val action = ListFragmentDirections.listFragmentToInfoFragment(pokemon)
                    findNavController(root).navigate(action)
                }
                Glide.with(pokemonImage)
                    .load(pokemon.sprites)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(pokemonImage)
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = PokemonItemBinding.bind(itemView)
    }
}

private object PokemonDiffItemCallback : DiffUtil.ItemCallback<UIPokemon>() {
    override fun areItemsTheSame(oldItem: UIPokemon, newItem: UIPokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UIPokemon, newItem: UIPokemon): Boolean {
        return oldItem.name == newItem.name &&
               oldItem.height == newItem.height &&
               oldItem.sprites == newItem.sprites &&
               oldItem.weight == newItem.weight &&
               oldItem.types == newItem.types
    }
}