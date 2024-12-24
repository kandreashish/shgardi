package com.example.testscrollingapp.repository.helper

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZGE2ZTI0MzY3M2UzNzQwYTZmZWMyOTgzNDNiMWNmYiIsIm5iZiI6MTYzOTQ1NzM0Ny41MTksInN1YiI6IjYxYjgyMjQzZmJlMzZmMDA4Y2E1MmUyNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SQIe1Az04egbmw0KaUH-1uhIAGFeQ6MnhFneP5K9L-c")
            .build()
        return chain.proceed(newRequest)
    }
}