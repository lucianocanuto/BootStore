package com.btcodans.bootstore.logo

import android.graphics.Color
import androidx.core.text.buildSpannedString
import androidx.core.text.color

fun BootStoreLogo() = buildSpannedString {
    color(Color.parseColor("#feb43e")) {append("Boot")}
    color(Color.parseColor("#724d06")) {append("Store✨")}
}