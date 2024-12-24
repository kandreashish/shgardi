package com.example.testscrollingapp.aboutscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testscrollingapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutSectionViewmodel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {


    fun loadAboutMovie(id: String){
        viewModelScope.launch{
            val response = movieRepository.loadDetailAboutMovie(id)
        }
    }
}