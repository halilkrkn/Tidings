package com.example.tidings.data.model


data class TidingsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)