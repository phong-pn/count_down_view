package com.phongpn.countdown.config

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import com.phongpn.countdown.util.dp
import com.phongpn.countdown.util.updateMargin

class Suffix {
    private var viewConfig: ViewConfig? = null
    private var background: Drawable? = null

    internal var suffixDay: String = ":"
    internal var suffixHour: String = ":"
    internal var suffixMinute: String = ":"
    internal var suffixSecond: String = ":"

    private var margin: Float? = 2.dp
    private var marginLeft: Float? = null
    private var marginRight: Float? = null
    private var marginTop: Float? = null
    private var marginBottom: Float? = null

    fun viewConfig(
        font: Typeface? = null,
        textSize: Float? = null,
        color: Int? = null
    ) = apply {
        viewConfig = ViewConfig(
            font = font ?: viewConfig?.font,
            textSize = textSize ?: viewConfig?.textSize,
            color = color ?: viewConfig?.color
        )
    }

    fun background(drawable: Drawable) = apply {
        background = drawable
    }

    fun margin(
        all: Float? = margin,
        l: Float? = marginLeft,
        t: Float? = marginTop,
        r: Float? = marginRight,
        b: Float? = marginBottom
    ) = apply {
        marginLeft = l
        marginTop = t
        marginRight = r
        marginBottom = b
        margin = all
    }

    fun text(
        suffixDay: String = ":",
        suffixHour: String = ":",
        suffixMinute: String = ":",
        suffixSecond: String = ":",
    ) = apply {
        this.suffixDay = suffixDay
        this.suffixHour = suffixHour
        this.suffixMinute = suffixMinute
        this.suffixSecond = suffixSecond
    }

    internal fun apply(suffixTextView: TextView, suffix: String) {
        suffixTextView.updateMargin(
            margin?.toInt(),
            marginLeft?.toInt(),
            marginRight?.toInt(),
            marginTop?.toInt(),
            marginBottom?.toInt()
        )
        background?.let {
            suffixTextView.apply {
                background = it
                updateLayoutParams {
                    width = it.intrinsicWidth
                    height = it.intrinsicHeight
                }
                text = ""
            }
        } ?: kotlin.run {
            suffixTextView.apply {
                text = suffix
                viewConfig?.font?.let { typeface = it }
                viewConfig?.textSize?.let { setTextSize(TypedValue.COMPLEX_UNIT_SP, it)}
                viewConfig?.color?.let { setTextColor(it) }
            }
        }
    }
}