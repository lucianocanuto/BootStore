package com.btcodans.bootstore.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Postagem(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
) : Parcelable