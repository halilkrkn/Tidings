package com.example.tidings.utils

import com.example.tidings.BuildConfig
import com.example.tidings.utils.Constants.Companion.API_KEY

class Constants {
    companion object{

        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = BuildConfig.NEWS_API_KEY
        const val TIDINGS_DATABASE_NAME = "tidings.db"
    }

}