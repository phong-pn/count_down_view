package com.phongpn.countdown.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.updateMargin(all: Int? = null, l: Int? = null, r: Int? = null, t: Int? = null, b: Int? = null) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    all?.let { params.setMargins(it) }
    params.setMargins(
        l ?: marginLeft,
        r ?: marginEnd,
        t ?: marginTop,
        b ?: marginBottom
    )
    layoutParams = params
}
