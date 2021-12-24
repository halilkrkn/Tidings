package com.example.tidings.utils

import com.example.tidings.BuildConfig

class Constants {
    companion object{

        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = BuildConfig.NEWS_API_KEY
        const val TIDINGS_DATABASE_NAME = "tidings.db"
    }

}