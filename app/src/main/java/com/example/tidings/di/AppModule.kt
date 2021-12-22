package com.example.tidings.di

import com.example.tidings.service.api.TidingsApi
import com.example.tidings.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // HttpLoggingInterceptor ile ağ işlemleri içersindeki tafiğği kayıt etmek için ve
    @Singleton
    @Provides
    fun logging(logging: HttpLoggingInterceptor): HttpLoggingInterceptor {
        return logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun client(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging) //Interceptor bir api isteğini takip edebilen, yeniden oluşturulabilen ve tekrardan istek atmasını sağlayan yapılardır.
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): TidingsApi =
        retrofit.create(TidingsApi::class.java)


}