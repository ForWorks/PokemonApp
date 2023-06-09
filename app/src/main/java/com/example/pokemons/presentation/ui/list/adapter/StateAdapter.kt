package com.example.pokemons.presentation.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.databinding.ErrorFooterBinding

class StateAdapter(private val retry: () -> Unit) : LoadStateAdapter<StateAdapter.ViewHolder>() {

    private lateinit var binding: ErrorFooterBinding

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = ErrorFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(state: LoadState) {
            binding.apply {
                loadingBar.isVisible = state is LoadState.Loading
                errorText.isVisible = state is LoadState.Error
                refreshButton.isVisible = state is LoadState.Error
            }
            binding.refreshButton.setOnClickListener { retry() }
        }
    }
}