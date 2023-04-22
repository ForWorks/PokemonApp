package com.example.pokemons.presentation.ui.pokemonList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.model.PokemonList
import com.example.pokemons.databinding.PokemonItemBinding
import retrofit2.Response.error
import javax.inject.Inject

class PokemonAdapter @Inject constructor():
    PagingDataAdapter<Pokemon, PokemonAdapter.ViewHolder>(PokemonDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
           // val pokemon = pokemonList[position]
            pokemonName.text = getItem(position)?.name
            Glide.with(root)
                .load(getItem(position)?.sprites?.front_default)
                //.listener(holder.listener)
                .error(R.drawable.ic_launcher_foreground)
                .into(pokemonImage)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = PokemonItemBinding.bind(itemView)
    }
}

private object PokemonDiffItemCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }
}