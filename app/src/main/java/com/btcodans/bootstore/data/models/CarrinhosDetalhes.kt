package com.btcodans.bootstore.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarrinhosDetalhes(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String
): Parcelable