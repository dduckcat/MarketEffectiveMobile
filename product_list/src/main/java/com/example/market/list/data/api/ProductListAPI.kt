package com.example.market.list.data.api

import com.example.market.list.data.models.ProductListModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductListAPI {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getProductList(): Response<ProductListModel>
}