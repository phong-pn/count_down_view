package com.phongpn.countdown.config

import android.graphics.Color
import android.graphics.Typeface

data class TextConfig(
    var timeFont: Typeface? = null,
    var timeTextSize: Int? = null,
    var timeColor: Int? = null,

    var suffixFont: Typeface? = null,
    var suffixTextSize: Int? = null,
    var suffixColor: Int? = null,
)