package com.example.testscrollingapp.repository

import com.example.testscrollingapp.repository.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET(NetworkConstants.GET_POPULAR_PEOPLE)
    suspend fun loadPopularCelebs() : List<Movie>

    @GET(NetworkConstants.GET_POPULAR_PEOPLE_DETAIL)
    suspend fun loadCelebDetail(@Path("person_id") personID : String) : Movie

}