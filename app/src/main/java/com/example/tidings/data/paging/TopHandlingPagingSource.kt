package com.example.tidings.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tidings.api.TidingsApiService
import com.example.tidings.data.model.TidingsArticle
import com.example.tidings.utils.Constants
import com.example.tidings.utils.Constants.Companion.UNSPLASH_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHandlingPagingSource @Inject constructor(
    private val tidingsApiService: TidingsApiService,
    private val category: String
) : PagingSource<Int, TidingsArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TidingsArticle> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = tidingsApiService.topHandlingTidings(category, position, params.loadSize)
            val topHandlingTidings = response.articles

            LoadResult.Page(
                data = topHandlingTidings,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (topHandlingTidings.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, TidingsArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}