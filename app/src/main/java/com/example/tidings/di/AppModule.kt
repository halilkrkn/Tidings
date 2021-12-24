package com.example.tidings.di

import android.content.Context
import androidx.room.Room
import com.example.tidings.BuildConfig
import com.example.tidings.data.db.TidingsDatabase
import com.example.tidings.api.TidingsApiService
import com.example.tidings.utils.Constants.Companion.BASE_URL
import com.example.tidings.utils.Constants.Companion.TIDINGS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // TODO: 22.12.2021  Api Kurulumu Yapıldı. 
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): TidingsApiService =
        retrofit.create(TidingsApiService::class.java)


    // TODO: 22.12.2021 Room Database Kurulumu Yapıldı. 
    @Singleton
    @Provides
    fun provideTidingsDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        TidingsDatabase::class.java,
        TIDINGS_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db:TidingsDatabase) = db.getTidingsDao()


}