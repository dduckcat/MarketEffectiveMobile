package com.example.market.list.data.repositories

import com.example.market.list.data.api.ProductListAPI
import org.koin.core.annotation.Factory

@Factory
class ProductListRepository(
    private val productListAPI: ProductListAPI
) {
    @Suppress("RedundantWith")
    suspend fun getProductList() = productListAPI.getProductList().body()!!
}