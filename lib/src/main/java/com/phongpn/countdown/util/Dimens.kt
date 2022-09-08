package com.phongpn.countdown.util

import android.content.res.Resources

val Number.dp: Float
    get() {
        val scale = Resources.getSystem().displayMetrics.density
        return this.toFloat() * scale + 0.5f
    }

val Number.sp: Float
    get() {
        val scale = Resources.getSystem().displayMetrics.scaledDensity
        return this.toFloat() * scale
    }

val Number.px2sp: Float
    get() {
        val scale = Resources.getSystem().displayMetrics.scaledDensity
        return this.toFloat() / scale
    }