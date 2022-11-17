package com.example.market.my_cart.data.repositories

import com.example.market.my_cart.data.api.MyCartAPI


class MyCartRepository(
    private val myCartAPI: MyCartAPI
) {

    suspend fun getProductDetails() = myCartAPI.getMyCart().body()!!
}