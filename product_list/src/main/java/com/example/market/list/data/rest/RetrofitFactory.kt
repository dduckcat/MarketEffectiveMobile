package com.example.market.list.data.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    fun <T> getApiInterface(apiClass: Class<T>): T =
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)
}