package com.example.mycityapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecommendationsViewModel : ViewModel() {
    private var currentLoadedCategoryId: String? = null
    private val _uiState = MutableStateFlow(RecommendationsUiState())

    fun loadRecommendations(categoryId: String) {
        if (categoryId == currentLoadedCategoryId) {
            return
        }

        currentLoadedCategoryId = categoryId

        val recommendations =
            LocalDataProvider.getPlacesByCategory(currentLoadedCategoryId.orEmpty())

        _uiState.update { currentState ->
            currentState.copy(
                recommendations = recommendations,
                selectedPlace = recommendations.firstOrNull()
            )
        }
    }

    val uiState: StateFlow<RecommendationsUiState> = _uiState

    fun updateSelectedPlace(placeId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedPlace = LocalDataProvider.getPlace(placeId)
            )
        }
    }

    private val _selectedPlace = MutableStateFlow<Place?>(null)
    val selectedPlace: StateFlow<Place?> = _selectedPlace

    fun loadPlaceDetails(placeId: String) {
        viewModelScope.launch {
            _selectedPlace.value = LocalDataProvider.getPlace(placeId)
        }
    }

}