package com.example.testscrollingapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testscrollingapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    fun loadMoviesList(){
        viewModelScope.launch{
            val response = movieRepository.loadMovies()

        }
    }

}