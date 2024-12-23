package com.example.testscrollingapp.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object HomeScreen : Routes()



    @Serializable
    data class AboutSection(
        val name: String?,
    ) : Routes()



}