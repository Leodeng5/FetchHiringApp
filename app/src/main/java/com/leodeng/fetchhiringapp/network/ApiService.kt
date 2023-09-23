package com.leodeng.fetchhiringapp.network

import com.leodeng.fetchhiringapp.data.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
        private val apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        fun getInstance(): ApiService = apiService
    }
}