package com.example.market.details.data.repositories

import com.example.market.details.data.api.ProductDetailsAPI

class ProductDetailsRepository(
    private val productDetailsAPI: ProductDetailsAPI
) {

    suspend fun getProductDetails() = productDetailsAPI.getProductList().body()!!
}