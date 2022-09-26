package com.phongpn.countdown.config

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import com.phongpn.countdown.util.dp
import com.phongpn.countdown.util.logd
import com.phongpn.countdown.util.updateMargin

class Text {
    private var timePadding: Float? = 2.dp
    private var timePaddingLeft: Float? = null
    private var timePaddingRight: Float? = null
    private var timePaddingTop: Float? = null
    private var timePaddingBottom: Float? = null

    private var timeMargin: Float? = 2.dp
    private var timeMarginLeft: Float? = null
    private var timeMarginRight: Float? = null
    private var timeMarginTop: Float? = null
    private var timeMarginBottom: Float? = null

    private var offset: Offset? = null

    private var timeBackground: Background? = null

    private var viewConfig: ViewConfig? = null

    fun padding(
        all: Float? = timePadding,
        l: Float? = timePaddingLeft,
        t: Float? = timePaddingTop,
        r: Float? = timePaddingRight,
        b: Float? = timePaddingBottom
    ) = apply {
        timePaddingLeft = l
        timePaddingTop = t
        timePaddingRight = r
        timePaddingBottom = b
        timePadding = all
    }

    fun margin(
        all: Float? = timeMargin,
        l: Float? = timeMarginLeft,
        t: Float? = timeMarginTop,
        r: Float? = timeMarginRight,
        b: Float? = timeMarginBottom
    ) = apply {
        timeMarginLeft = l
        timeMarginTop = t
        timeMarginRight = r
        timeMarginBottom = b
        timeMargin = all
    }

    fun offset(
        all: Int? = null,
        l: Int? = null,
        t: Int? = null,
        r: Int? = null,
        b: Int? = null
    ) = apply {
        offset = if (all != null) Offset(all, all, all, all)
        else Offset(
            l ?: offset?.left,
            t ?: offset?.top,
            r ?: offset?.right,
            b ?: offset?.bottom
        )
    }

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

    fun background(drawable: Drawable? = null, cornerRadius: Float = 2.dp, color: Int? = null) = apply {
        timeBackground = Background(cornerRadius, color, drawable)
    }

    internal fun apply(text: TextView, background: View) {
        timePadding?.let { text.setPadding(it.toInt()) }
        timePaddingLeft?.let { text.updatePadding(left = it.toInt()) }
        timePaddingRight?.let { text.updatePadding(right = it.toInt()) }
        timePaddingTop?.let { text.updatePadding(top = it.toInt()) }
        timePaddingBottom?.let { text.updatePadding(bottom = it.toInt()) }

        text.updateMargin(
            timeMargin?.toInt(),
            timeMarginLeft?.toInt(),
            timeMarginRight?.toInt(),
            timeMarginTop?.toInt(),
            timeMarginBottom?.toInt()
        )

        timeBackground?.let {
            background.background = it.drawable ?: GradientDrawable().apply {
                it.color?.let { it1 -> setColor(it1) }
                it.cornerRadius?.let { cornerRadius = it }
            }
        }

        offset?.let {
            background.updateMargin(l = it.left, r = it.right, t = it.top, b = it.bottom)
        }

        text.apply {
            viewConfig?.font?.logd() ?: "fontnull".logd()
            viewConfig?.font?.let { typeface = it }
            viewConfig?.textSize?.let { setTextSize(TypedValue.COMPLEX_UNIT_SP, it) }
            viewConfig?.color?.let { setTextColor(it) }
        }
    }


    data class Background(
        internal var cornerRadius: Float? = null,
        internal var color: Int? = null,
        internal var drawable: Drawable? = null
    )

    data class Offset(
        internal var left: Int? = null,
        internal var top: Int? = null,
        internal var right: Int? = null,
        internal var bottom: Int? = null
    )
}
