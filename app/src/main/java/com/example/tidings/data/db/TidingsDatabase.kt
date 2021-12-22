package com.example.tidings.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tidings.data.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class TidingsDatabase: RoomDatabase() {

    abstract fun getTidingsDao(): TidingsDao
}