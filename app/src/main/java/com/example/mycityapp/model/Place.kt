package com.example.mycityapp.model

data class Place(
    val id: String,
    val categoryId: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val address: String,
    val rating: Double,
    val tags: List<String>
)