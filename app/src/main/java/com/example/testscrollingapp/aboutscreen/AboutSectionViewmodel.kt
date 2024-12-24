package com.example.testscrollingapp.aboutscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testscrollingapp.repository.CelebRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutSectionViewmodel @Inject constructor(private val celebRepository: CelebRepository) : ViewModel() {


    private val _state = MutableStateFlow(AboutCelebScreenState())
    val state = _state as StateFlow<AboutCelebScreenState>

    fun getCelebDetail(id: String) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val response = celebRepository.loadCelebDetail(id)
            if (response.isSuccessful) {
                _state.value = _state.value.copy(
                    celebrity = response.body(),
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    celebrity = null,
                    isLoading = false
                )
            }
        }
    }
}