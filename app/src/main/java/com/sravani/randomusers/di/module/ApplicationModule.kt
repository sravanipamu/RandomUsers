package com.sravani.randomusers.di.module

import com.sravani.randomusers.data.api.NetworkService
import com.sravani.randomusers.di.BaseUrl
import com.sravani.randomusers.di.ResultCount
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule() {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://randomuser.me/"

    @ResultCount
    @Provides
    fun provideResultCount() : Int = 10


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
       @BaseUrl baseUrl: String,  gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }
}