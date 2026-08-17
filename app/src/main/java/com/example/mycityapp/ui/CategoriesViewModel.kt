package com.example.mycityapp.ui

import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.data.LocalDataProvider.defaultCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoriesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        CategoriesUiState(
            categories = LocalDataProvider.getCategories(),
            selectedCategory = LocalDataProvider.getCategories().getOrElse(
                0,
                defaultValue = { defaultCategory }
            )
        )
    )

    val uiState: StateFlow<CategoriesUiState> = _uiState
}