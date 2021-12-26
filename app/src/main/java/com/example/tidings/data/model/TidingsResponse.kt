package com.example.tidings.data.model


data class TidingsResponse(
    val articles: List<TidingsArticle>,
    val status: String,
    val totalResults: Int
)