package com.example.mycityapp

import com.example.mycityapp.ui.CategoriesViewModel
import com.example.mycityapp.ui.RecommendationsViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class MyCityAppViewModelTest {
    private val viewModelCategories = CategoriesViewModel()
    private val viewModelRecommendations = RecommendationsViewModel()

    @Test
    fun categoriesViewModel_CheckInitialState() {
        //Arrange
        val initialState = viewModelCategories.uiState.value

        //Assert
        assertEquals("cafes", initialState.selectedCategory.id)
    }

    @Test
    fun recommendationsViewModel_CheckInitialState() {
        val initialState = viewModelRecommendations.uiState.value
        assertEquals(0, initialState.recommendations.size)
        assertEquals(null, initialState.selectedPlace)
    }

    @Test
    fun recommendationsViewModel_LoadRecommendations_SetsFirstItemAsDefault() {
        //Arrange - Load recommendations for restaurant category
        viewModelRecommendations.loadRecommendations("restaurants")
        val state = viewModelRecommendations.uiState.value

        //Assert - check if recommendations are not empty & first recommendation is selected
        assertEquals(false, state.recommendations.isEmpty())
        assertEquals(state.recommendations[0], state.selectedPlace)

    }

    @Test
    fun recommendationsViewModel_UpdateSelectedPlace_UpdatesUiState() {
        //Arrange
        viewModelRecommendations.loadRecommendations("attractions")
        val recommendations = viewModelRecommendations.uiState.value.recommendations
        val selectedPlaceId = recommendations[3].id

        //Act - Select the place based on selectedPlaceId
        viewModelRecommendations.updateSelectedPlace(selectedPlaceId)

        //Assert
        assertEquals(selectedPlaceId, viewModelRecommendations.uiState.value.selectedPlace?.id)

    }

}