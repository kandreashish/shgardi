package com.example.testscrollingapp.celeb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testscrollingapp.repository.CelebRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebListScreenViewModel @Inject constructor(private val celebRepository: CelebRepository) : ViewModel() {

    private val _state = MutableStateFlow(CelebListScreenState())
    val state = _state as StateFlow<CelebListScreenState>

    var page = 1

    private fun loadCelebList(page: Int = 1) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val response = celebRepository.loadPopularCelebs(page)
            if (response.isSuccessful) {
                _state.value = _state.value.copy(
                    searchResults = response.body()?.people ?: emptyList(),
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    searchResults = emptyList(),
                    isLoading = false
                )
            }
        }
    }

    fun onCelebAction(action: CelebListAction?) {
        when (action) {
            is CelebListAction.OnCelebClick -> {

            }
            is CelebListAction.LoadMore -> {
                loadCelebList()
            }
            is CelebListAction.OnSearchQueryChange -> {
                when (action.query) {
                    "" -> {
                        _state.update {
                            it.copy(searchQuery = "")
                        }
                        loadCelebList()
                    }
                    else -> {
                        _state.update {
                            it.copy(searchQuery = action.query)
                        }
                        viewModelScope.launch {
                            searchCelebs(action.query?:"")
                        }
                    }
                }
            }

            null -> loadCelebList()
        }
    }


    private fun searchCelebs(searchQuery:String) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val response = celebRepository.searchPeople(searchQuery)
            if (response.isSuccessful) {
                _state.value = _state.value.copy(
                    searchResults = response.body()?.people ?: emptyList(),
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    searchResults = emptyList(),
                    isLoading = false
                )
            }
        }
    }

}