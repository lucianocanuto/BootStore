package com.btcodans.bootstore.data.models

data class Carrinho(
    val id: Int,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val products: List<ItemCarrinho>,
    val totalProducts: Int,
    val totalQuantity: Int
)
