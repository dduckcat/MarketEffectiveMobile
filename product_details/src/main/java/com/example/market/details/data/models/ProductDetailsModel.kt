package com.example.market.details.data.models

data class ProductDetailsModel(
    val id: Int,
    val title: String,
    val rating: Double,
    var isFavorites: Boolean = true,
    val price: Double,
    val ssd: String,
    val sd: String,
    val CPU: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val images: List<String>
) {
    companion object {
        val EMPTY = ProductDetailsModel(
            0,
            "",
            0.0,
            false,
            0.0,
            "",
            "",
            "",
            "",
            emptyList(),
            emptyList(),
            emptyList()
        )
    }
}