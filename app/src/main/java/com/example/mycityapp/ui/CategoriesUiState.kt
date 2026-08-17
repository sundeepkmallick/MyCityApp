package com.example.mycityapp.ui

import com.example.mycityapp.model.Category

data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category
)