package com.phongpn.countdown.countdown

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.phongpn.countdown.R
import com.phongpn.countdown.config.Display
import com.phongpn.countdown.config.Suffix
import com.phongpn.countdown.config.Text
import com.phongpn.countdown.util.px2sp

class CountDownView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    style: Int = 0
) : FrameLayout(context, attrs, style) {
    private lateinit var timer: CountDownTimer
    private var container: View

    private var display = Display()
    private var suffix = Suffix()
    private var text = Text()

    private var tvDay: TextView
    private var tvSuffixDay: TextView
    private val tvHour: TextView
    private val tvSuffixHour: TextView
    private val tvMinute: TextView
    private val tvSuffixMinute: TextView
    private val tvSecond: TextView
    private val tvSuffixSecond: TextView
    private val tvMilliSecond: TextView

    private val backgroundDay: View
    private val backgroundHour: View
    private val backgroundMinute: View
    private val backgroundSecond: View
    private val backgroundMillisecond: View

    init {
        inflate(context, R.layout.layout_count_down, this)
        container = findViewById(R.id.container)
        tvDay = container.findViewById(R.id.tv_day)
        tvSuffixDay = container.findViewById(R.id.tv_suffix_day)
        backgroundDay = container.findViewById(R.id.background_day)
        tvHour = container.findViewById(R.id.tv_hour)
        tvSuffixHour = container.findViewById(R.id.tv_suffix_hour)
        backgroundHour = container.findViewById(R.id.background_hour)
        tvMinute = container.findViewById(R.id.tv_minute)
        tvSuffixMinute = container.findViewById(R.id.tv_suffix_minute)
        backgroundMinute = container.findViewById(R.id.background_minute)
        tvSecond = container.findViewById(R.id.tv_second)
        tvSuffixSecond = container.findViewById(R.id.tv_suffix_second)
        backgroundSecond = container.findViewById(R.id.background_second)
        tvMilliSecond = container.findViewById(R.id.tv_millisecond)
        backgroundMillisecond = container.findViewById(R.id.background_millisecond)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownView)
        applyTypeArray(ta)
        ta.recycle()

        applyDisplay(display)
        applySuffix(suffix)
        applyModifier()
    }

    protected fun applyTypeArray(typedArray: TypedArray) {
        text.apply {
            val textSize =
                typedArray.getDimensionPixelSize(
                    R.styleable.CountDownView_timeTextSize,
                    14
                ).px2sp
            val fontId = typedArray.getResourceId(R.styleable.CountDownView_timeFont, -1)
            val font = if (fontId != -1) ResourcesCompat.getFont(context, fontId) else null
            val color = typedArray.getColor(R.styleable.CountDownView_timeColor, Color.BLACK)
            viewConfig(font, textSize, color)

            val backgroundColor =
                typedArray.getColor(R.styleable.CountDownView_background_color, Color.WHITE)
            val corner = typedArray.getDimension(R.styleable.CountDownView_cornerRadius, 8f)
            background(cornerRadius = corner, color = backgroundColor)
        }

        suffix.apply {
            val backgroundSuffixId =
                typedArray.getResourceId(R.styleable.CountDownView_background_suffix, -1)
            if (backgroundSuffixId != -1) {
                this.background(ContextCompat.getDrawable(context, backgroundSuffixId)!!)
            }
        }
    }


    protected fun applyDisplay(display: Display) {
        display.apply {
            tvDay.isVisible = showDay
            tvSuffixDay.isVisible = showDay && showHour

            tvHour.isVisible = showHour
            tvSuffixHour.isVisible = showHour && showMinute

            tvMinute.isVisible = showMinute
            tvSuffixMinute.isVisible = showHour && showMinute

            tvSecond.isVisible = showSecond
            tvSuffixSecond.isVisible = showSecond && showMillisecond

            tvMilliSecond.isVisible = showMillisecond
        }
    }

    protected fun applyModifier() {
        text.apply(tvDay, backgroundDay)
        text.apply(tvHour, backgroundHour)
        text.apply(tvMinute, backgroundMinute)
        text.apply(tvSecond, backgroundSecond)
        text.apply(tvMilliSecond, backgroundMillisecond)
    }

    protected fun applySuffix(suffix: Suffix) {
        suffix.apply {
            apply(tvSuffixDay, suffixDay)
            apply(tvSuffixHour, suffixHour)
            apply(tvSuffixMinute, suffixMinute)
            apply(tvSuffixSecond, suffixSecond)
        }
    }

    fun suffix(block: Suffix.() -> Unit) = apply {
        block(suffix)
        applySuffix(suffix)
    }

    fun display(block: Display.() -> Unit) = apply {
        block(display)
        applyDisplay(display)
    }

    fun text(block: Text.() -> Unit) = apply {
        block(text)
        applyModifier()
    }

    fun start(timeInMilli: Long) {
        val interval = if (display.showMillisecond) 10 else 1000
        timer = object : CountDownTimer(timeInMilli, interval.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                updateTime(millisUntilFinished)
            }

            override fun onFinish() {
                updateTime(0)
            }
        }
        timer.start()
    }

    fun startUtil(timeInMilli: Long) {
        val remain = timeInMilli - System.currentTimeMillis()
        start(remain)
    }


    private fun updateTime(millisUntilFinished: Long) {
        val day = (millisUntilFinished / (1000 * 60 * 60 * 24)).toInt()
        val hour =
            if (display.showDay) (millisUntilFinished / (1000 * 60 * 60 * 24) / (1000 * 60 * 60)).toInt()
            else (millisUntilFinished / (1000 * 60 * 60)).toInt()
        val minute = (millisUntilFinished % (1000 * 60 * 60) / (1000 * 60)).toInt()
        val second = (millisUntilFinished % (1000 * 60) / 1000).toInt()
        val millisecond = (millisUntilFinished % 1000).toInt()
        tvDay.text = format2Digit(day)
        tvHour.text = format2Digit(hour)
        tvMinute.text = format2Digit(minute)
        tvSecond.text = format2Digit(second)
        tvMilliSecond.text = format2Digit(millisecond)
    }

    private fun format2Digit(number: Int) = String.format("%02d", number)


}
