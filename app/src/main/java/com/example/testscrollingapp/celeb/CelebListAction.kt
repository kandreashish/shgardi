package com.example.testscrollingapp.celeb

import com.example.testscrollingapp.repository.model.Celebrity

sealed interface CelebListAction {
    data class OnSearchQueryChange(val query: String?): CelebListAction
    data class OnCelebClick(val celebrity: Celebrity): CelebListAction
}