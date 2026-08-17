package com.example.mycityapp.ui

import com.example.mycityapp.model.Place

data class RecommendationsUiState(
    val recommendations: List<Place> = emptyList(),
    val selectedPlace: Place? = null
)