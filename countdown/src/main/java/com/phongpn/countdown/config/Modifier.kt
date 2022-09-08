package com.phongpn.countdown.config

import android.graphics.Color
import android.graphics.Typeface
import com.phongpn.countdown.util.dp

data class Modifier(
    val timePadding: Float? = 2.dp,
    val timePaddingLeft: Float? = null,
    val timePaddingRight: Float? = null,
    val timePaddingTop: Float? = null,
    val timePaddingBottom: Float? = null,

    val timeMargin: Float? = 2.dp,
    val timeMarginLeft: Float? = null,
    val timeMarginRight: Float? = null,
    val timeMarginTop: Float? = null,
    val timeMarginBottom: Float? = null,

    var backgroundColor: Int? = null,
    var corner: Int? = null
)
