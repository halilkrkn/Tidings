package com.example.tidings.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.tidings.api.TidingsApiService
import com.example.tidings.data.db.TidingsDatabase
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.data.paging.TidingsPagingSource
import com.example.tidings.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TidingsRepository @Inject constructor(
    private val tidingsApiService: TidingsApiService,
    private val tidingsDatabase: TidingsDatabase
) {

    // Buraya Haberlerin nasıl sayfalandırmamız gerekiyor ve onu tidingsApiService çekeceğimizi tanımladık ve TidingsPagingSource gönderdik bilgileri.
    fun getTidingsSearchResults(query: String) =
        Pager(
            // Burada pagination olan sayfalar yapılandırıldı.
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                maxSize= 100,
                enablePlaceholders = false
            ),
            // UnsplashPagingSource u repository e ekledik.
            pagingSourceFactory = {
                TidingsPagingSource(tidingsApiService, query)
            }
        ).liveData


    suspend fun insertArticleTidings(article : TidingsArticle) = tidingsDatabase.getTidingsDao().insert(article)

    suspend fun deleteArticleTidings(article: TidingsArticle) = tidingsDatabase.getTidingsDao().delete(article)

    fun saveArticle() = tidingsDatabase.getTidingsDao().getAllArticle()

    fun searchSavedName(searchPhotos: String) = tidingsDatabase.getTidingsDao().searchSavedName(searchPhotos)

}