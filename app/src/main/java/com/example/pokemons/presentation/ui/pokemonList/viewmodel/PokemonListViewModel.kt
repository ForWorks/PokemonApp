package com.example.pokemons.presentation.ui.pokemonList.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.paging.PokemonPagingSource
import com.example.pokemons.storage.remote.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    val isLoading = MutableLiveData(false)

    val list = Pager(PagingConfig(1)) {
        PokemonPagingSource(pokemonRepository)
    }.flow.cachedIn(viewModelScope)



















//    private val pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData(listOf())
//    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
//    private val error: MutableLiveData<String?> = MutableLiveData(null)
//    private var currentItems: Int = 0
//    private var job: Job? = null
//
//    init {
//        fetchNext()
//    }
//
//    override fun observePokemonList(owner: LifecycleOwner, observer: Observer<List<Pokemon>>) {
//        pokemonList.observe(owner, observer)
//    }
//
//    override fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) {
//        isLoading.observe(owner, observer)
//    }
//
//    override fun observeError(owner: LifecycleOwner, observer: Observer<String?>) {
//        error.observe(owner, observer)
//    }
//
//   // override fun errorExists(): Boolean = error.value!!.isNotEmpty()
//
//    override fun fetchNext() {
////        if (job != null) {
////            return
////        }
////        isLoading.value = true
////        error.value = null
////        CoroutineScope(Dispatchers.Default).launch {
////            val result = pokemonRepository.getPokemonList(currentItems)
////            result.onSuccess {
////                currentItems += it.size
////                pokemons.value = pokemons.value!! + it
////            }
////            result.onFailure {
////                error.value = it.message ?: "Something went wrong"
////                pokemons.value = pokemons.value
////            }
////            isLoading.value = false
//////            job = null
////        }
//    }
}