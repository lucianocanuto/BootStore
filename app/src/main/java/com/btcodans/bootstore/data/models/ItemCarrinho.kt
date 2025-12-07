package com.btcodans.bootstore.data.models

data class ItemCarrinho(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val thumbnail: String,
    val discountPercentage: Double,
    val discountedTotal: Double
)
