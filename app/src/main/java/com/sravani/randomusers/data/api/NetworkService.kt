package com.sravani.randomusers.data.api


import com.sravani.randomusers.data.model.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("api/")
    suspend fun getRandomUsers(@Query("results") results: Int): RandomUserResponse

}