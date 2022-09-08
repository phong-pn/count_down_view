package com.phongpn.countdown.util

import android.content.res.Resources

val Number.dp: Float
    get() {
        val scale = Resources.getSystem().displayMetrics.density
        return this.toFloat() * scale + 0.5f
    }