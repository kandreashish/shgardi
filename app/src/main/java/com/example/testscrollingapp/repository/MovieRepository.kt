package com.example.testscrollingapp.repository

import com.example.testscrollingapp.repository.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    suspend fun loadMovies(): List<Movie> {
        return movieApi.loadMovies()
    }
}