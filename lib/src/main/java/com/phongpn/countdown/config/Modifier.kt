package com.phongpn.countdown.config

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.phongpn.countdown.util.dp

data class Modifier(
    var timePadding: Float? = 2.dp,
    var timePaddingLeft: Float? = null,
    var timePaddingRight: Float? = null,
    var timePaddingTop: Float? = null,
    var timePaddingBottom: Float? = null,

    var timeMargin: Float? = 2.dp,
    var timeMarginLeft: Float? = null,
    var timeMarginRight: Float? = null,
    var timeMarginTop: Float? = null,
    var timeMarginBottom: Float? = null,

    var backgroundColor: Int? = null,
    var corner: Int? = null,

    var suffixBackground: Drawable? = null
)
