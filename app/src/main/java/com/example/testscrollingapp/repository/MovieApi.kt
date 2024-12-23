package com.example.testscrollingapp.repository

import com.example.testscrollingapp.repository.model.Movie
import retrofit2.http.GET

interface MovieApi {

    @GET("")
    suspend fun loadMovies() : List<Movie>

}