package com.example.tidings.utils

import com.example.tidings.BuildConfig

class Constants {
    companion object{

        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = BuildConfig.NEWS_API_KEY
        const val TIDINGS_DATABASE_NAME = "tidings.db"
        const val UNSPLASH_STARTING_PAGE_INDEX  = 1
        const val NETWORK_PAGE_SIZE = 20
        const val DEFAULT_QUERY = "bitcoin"
    }

}