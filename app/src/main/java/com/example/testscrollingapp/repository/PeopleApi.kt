package com.example.testscrollingapp.repository

import com.example.testscrollingapp.repository.model.CelebDetails
import com.example.testscrollingapp.repository.model.PopularPeopleList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {

    @GET(NetworkConstants.GET_POPULAR_PEOPLE)
    suspend fun loadPopularCelebs(@Query("page") page: Int): Response<PopularPeopleList>

    @GET(NetworkConstants.GET_POPULAR_PEOPLE_DETAIL)
    suspend fun loadCelebDetail(@Path("person_id") personID: String): Response<CelebDetails>

    @GET(NetworkConstants.SEARCH_PEOPLE)
    suspend fun searchPeople(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): Response<PopularPeopleList>

}