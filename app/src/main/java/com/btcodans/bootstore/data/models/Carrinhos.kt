package com.btcodans.bootstore.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Carrinhos(
    val id: Int,
    val products: List<CarrinhosDetalhes>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
): Parcelable