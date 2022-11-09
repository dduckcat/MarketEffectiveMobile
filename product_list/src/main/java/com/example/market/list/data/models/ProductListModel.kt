package com.example.market.list.data.models

data class ProductListModel(
    val best_seller: List<BestSellerModel>,
    val home_store: List<HotSalesModel>
) {
    data class BestSellerModel(
        val id: Int,
        val is_favorites: Boolean,
        val title: String,
        val price_without_discount: Double,
        val discount_price: Double,
        val picture: String
    )

    data class HotSalesModel(
        val id: Int,
        val is_buy: Boolean,
        val is_new: Boolean,
        val picture: String,
        val subtitle: String,
        val title: String
    )

    companion object {
        val EMPTY = ProductListModel(emptyList(), emptyList())
    }
}