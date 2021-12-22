package com.example.tidings.service

import com.example.tidings.data.models.Article


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)