package com.example.tidings.repository

import com.example.tidings.data.db.TidingsDao
import com.example.tidings.service.api.TidingsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TidingsRepository @Inject constructor(
    private val tidingsApi: TidingsApi,
    private val tidingsDao: TidingsDao
) {
}