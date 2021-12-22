package com.example.tidings.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tidings.data.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface TidingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)

    @Query("SELECT * FROM tidings_table")
    fun getAllPhotos(): LiveData<List<Article>>

    @Query("SELECT * FROM tidings_table WHERE title LIKE '%' || :searchQuery || '%' ORDER BY title DESC")
    fun searchByTidings(searchQuery: String): Flow<List<Article>>

}