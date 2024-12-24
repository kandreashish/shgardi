package com.example.testscrollingapp.celeb

import com.example.testscrollingapp.repository.helper.UiText
import com.example.testscrollingapp.repository.model.Celebrity

data class CelebListScreenState(
    val searchQuery: String? = "",
    val searchResults: List<Celebrity> = emptyList(),
    val favoriteBooks: List<Celebrity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)