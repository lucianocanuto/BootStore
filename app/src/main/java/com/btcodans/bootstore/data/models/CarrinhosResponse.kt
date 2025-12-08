package com.btcodans.bootstore.data.models

data class CarrinhosResponse(
    val carts: List<Carrinhos>,
    val total: Int,
    val skip: Int,
    val limit: Int
)