package com.example.testscrollingapp.aboutscreen

import com.example.testscrollingapp.repository.helper.UiText
import com.example.testscrollingapp.repository.model.CelebDetails

data class AboutCelebScreenState(
    val celebrity: CelebDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)