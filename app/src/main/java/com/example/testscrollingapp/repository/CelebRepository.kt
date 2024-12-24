package com.example.testscrollingapp.repository

import com.example.testscrollingapp.repository.model.CelebDetails
import com.example.testscrollingapp.repository.model.Celebrity
import com.example.testscrollingapp.repository.model.PopularPeopleList
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CelebRepository @Inject constructor(private val peopleApi: PeopleApi) {

    suspend fun loadPopularCelebs(): Response<PopularPeopleList> {
        return peopleApi.loadPopularCelebs()
    }

    suspend fun loadCelebDetail(movieId: String): Response<CelebDetails> {
        return peopleApi.loadCelebDetail(movieId)
    }

    suspend fun searchPeople(query: String): Response<PopularPeopleList> {
        return peopleApi.searchPeople(query)
    }
}