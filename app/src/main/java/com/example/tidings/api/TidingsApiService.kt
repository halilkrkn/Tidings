package com.example.tidings.api

import com.example.tidings.data.model.TidingsResponse
import com.example.tidings.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// Apinin Servis arayüzünü yazdık.
interface TidingsApiService {

    // Burada dünyadaki tüm haberleri çekip gösteriyoruz
    @Headers("Authorization:$API_KEY")
    @GET("v2/everything")
    fun searchForTidings(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): TidingsResponse

    // Burada ise ülkelere göre haberleri çekiyoruz. Yani ülkelere göre filtreleyip haberleri çekiyoruz.
    @Headers("Authorization:$API_KEY")
    @GET("v2/top-headlines")
    fun breakingTidings(

        //        @Query("q") query: String,
        @Query("country") countryCode: String = "tr",
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): TidingsResponse


}