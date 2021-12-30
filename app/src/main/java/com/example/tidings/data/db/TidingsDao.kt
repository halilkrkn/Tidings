package com.example.tidings.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.room.*
import com.example.tidings.data.model.TidingsArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface TidingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(article: TidingsArticle)

    @Delete
    suspend fun delete(article: TidingsArticle)

    @Query("SELECT * FROM tidingsTable")
    fun getAllArticle(): LiveData<List<TidingsArticle>>

    @Query("SELECT * FROM tidingsTable WHERE title LIKE '%' || :searchQuery || '%' ORDER BY title DESC")
    fun searchByTidings(searchQuery: String): Flow<List<TidingsArticle>>

}