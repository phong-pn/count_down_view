package com.phongpn.countdown.config

import android.graphics.Color
import android.graphics.Typeface

data class TextConfig(
    var timeFont: Typeface? = null,
    var timeTextSize: Int = 14,
    var timeColor: Int = Color.BLACK,

    val suffixFont: Typeface? = null,
    val suffixTextSize: Int = 14,
    val suffixColor: Int = Color.BLACK,
)