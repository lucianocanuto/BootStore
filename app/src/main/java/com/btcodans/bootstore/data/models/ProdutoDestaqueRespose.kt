package com.btcodans.bootstore.data.models

data class ProdutoDestaqueRespose(
    val products: List<Produto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
