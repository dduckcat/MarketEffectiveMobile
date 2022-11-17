package com.example.market.details.data.api

import com.example.market.details.data.models.ProductDetailsModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductDetailsAPI {

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductList(): Response<ProductDetailsModel>
}