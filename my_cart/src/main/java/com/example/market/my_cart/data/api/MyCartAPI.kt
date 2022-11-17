package com.example.market.my_cart.data.api

import com.example.market.my_cart.data.models.MyCartModel
import retrofit2.Response
import retrofit2.http.GET


interface MyCartAPI {

    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getMyCart(): Response<MyCartModel>
}