package com.btcodans.bootstore.data.models

data class Produto(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val description: String,
    val brand: String?,
    val category: String?,
    val images: List<String>?,
    val rating: Double?
)
