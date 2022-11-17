package com.example.market.my_cart.data.models

data class MyCartModel(
    val id: Int,
    val total: Double,
    val delivery: String,
    var basket: List<BasketItem>,
) {
    data class BasketItem(
        val id: Int,
        val title: String,
        val images: String,
        val price: Double
    )

    companion object {
        val EMPTY = MyCartModel(
            0,
            0.0,
            "",
            emptyList()
        )
    }
}