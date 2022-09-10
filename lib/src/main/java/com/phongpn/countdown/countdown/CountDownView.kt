package com.phongpn.countdown.countdown

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import com.phongpn.countdown.R
import com.phongpn.countdown.config.Modifier
import com.phongpn.countdown.config.TextConfig
import com.phongpn.countdown.config.TimeConfig
import com.phongpn.countdown.util.px2sp
import com.phongpn.countdown.util.updateMargin

class CountDownView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    style: Int = 0
) : FrameLayout(context, attrs, style) {
    private lateinit var timer: CountDownTimer
    private var container: View

    private var mTimeConfig = TimeConfig()
    private var mTextConfig = TextConfig()
    private var mModifier = Modifier()

    private var tvDay: TextView
    private var tvSuffixDay: TextView
    private val tvHour: TextView
    private val tvSuffixHour: TextView
    private val tvMinute: TextView
    private val tvSuffixMinute: TextView
    private val tvSecond: TextView
    private val tvSuffixSecond: TextView
    private val tvMilliSecond: TextView

    init {
        inflate(context, R.layout.layout_count_down, this)
        container = findViewById(R.id.container)
        tvDay = container.findViewById(R.id.tv_day)
        tvSuffixDay = container.findViewById(R.id.tv_suffix_day)
        tvHour = container.findViewById(R.id.tv_hour)
        tvSuffixHour = container.findViewById(R.id.tv_suffix_hour)
        tvMinute = container.findViewById(R.id.tv_minute)
        tvSuffixMinute = container.findViewById(R.id.tv_suffix_minute)
        tvSecond = container.findViewById(R.id.tv_second)
        tvSuffixSecond = container.findViewById(R.id.tv_suffix_second)
        tvMilliSecond = container.findViewById(R.id.tv_millisecond)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownView)
        applyTypeArray(ta)
        ta.recycle()

        applyTimeConfig(mTimeConfig)
        applyTextConfig(mTextConfig)
        applyModifier(mModifier)
    }

    protected fun applyTypeArray(typedArray: TypedArray) {
        mTextConfig.apply {
            timeTextSize =
                typedArray.getDimensionPixelSize(R.styleable.CountDownView_timeTextSize, 14).px2sp.toInt()
            val fontId = typedArray.getResourceId(R.styleable.CountDownView_timeFont, -1)
            if (fontId != -1) timeFont = ResourcesCompat.getFont(context, fontId)
            timeColor = typedArray.getColor(R.styleable.CountDownView_timeColor, Color.BLACK)
        }

        mModifier.apply {
            val backgroundSuffixId = typedArray.getResourceId(R.styleable.CountDownView_background_suffix, -1)
            if (backgroundSuffixId != -1) suffixBackground = ContextCompat.getDrawable(context, backgroundSuffixId)
            backgroundColor =
                typedArray.getColor(R.styleable.CountDownView_background_color, Color.WHITE)
            corner = typedArray.getDimension(R.styleable.CountDownView_cornerRadius, 8f).toInt()
        }
    }


    protected fun applyTimeConfig(timeConfig: TimeConfig) {
        timeConfig.apply {
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

    protected fun applyModifier(modifier: Modifier) {
        modifier.apply {
            fun applyMargin(view: View) {
                view.updateMargin(
                    timeMargin?.toInt(),
                    timeMarginLeft?.toInt(),
                    timeMarginRight?.toInt(),
                    timeMarginTop?.toInt(),
                    timeMarginBottom?.toInt()
                )
            }

            fun applyPadding(view: View) {
                timePadding?.let { view.setPadding(it.toInt()) }
                timePaddingLeft?.let { view.updatePadding(left = it.toInt()) }
                timePaddingRight?.let { view.updatePadding(right = it.toInt()) }
                timePaddingTop?.let { view.updatePadding(top = it.toInt()) }
                timePaddingBottom?.let { view.updatePadding(bottom = it.toInt()) }
            }

            applyPadding(tvDay)
            applyPadding(tvHour)
            applyPadding(tvMilliSecond)
            applyPadding(tvSecond)
            applyPadding(tvMinute)

            applyMargin(tvDay)
            applyMargin(tvHour)
            applyMargin(tvMilliSecond)
            applyMargin(tvSecond)
            applyMargin(tvMinute)

            val d = try {
                tvDay.background as GradientDrawable? ?: GradientDrawable()
            }
                catch(e: Exception) {
                    GradientDrawable()
                }
            backgroundColor?.let { d.setColor(it) }
            corner?.toFloat()?.let { d.cornerRadius = it }

            tvDay.background = d
            tvHour.background = d
            tvMinute.background = d
            tvSecond.background = d
            tvMilliSecond.background = d

            suffixBackground?.let {
                tvSuffixDay.background = it
                tvSuffixHour.background = it
                tvSuffixMinute.background = it
                tvSuffixSecond.background = it
                tvSuffixDay.text = ""
                tvSuffixHour.text = ""
                tvSuffixMinute.text = ""
                tvSuffixSecond.text = ""
            }
        }
    }

    protected fun applyTextConfig(textConfig: TextConfig) {
        textConfig.apply {
            fun applyTimeText(view: TextView) {
                timeFont?.let { view.typeface = it }
                timeTextSize?.toFloat()?.let { view.setTextSize(TypedValue.COMPLEX_UNIT_SP,it) }
                timeColor?.let { view.setTextColor(it) }
            }

            fun applySuffixText(view: TextView) {
                suffixFont?.let { view.typeface = it }
                suffixTextSize?.toFloat()?.let { view.textSize = it }
                suffixColor?.let { view.setTextColor(it) }
            }

            applyTimeText(tvDay)
            applyTimeText(tvHour)
            applyTimeText(tvMinute)
            applyTimeText(tvSecond)
            applyTimeText(tvMilliSecond)

            applySuffixText(tvSuffixDay)
            applySuffixText(tvSuffixHour)
            applySuffixText(tvSuffixMinute)
            applySuffixText(tvSuffixSecond)
        }
    }

    fun text(block: TextConfig.() -> Unit)= apply {
        block(mTextConfig)
        applyTextConfig(mTextConfig)
    }

    fun time(block: TimeConfig.() -> Unit) = apply {
        block(mTimeConfig)
        applyTimeConfig(mTimeConfig)
    }

    fun modifier(block: Modifier.() -> Unit) = apply {
        block(mModifier)
        applyModifier(mModifier)
    }

    fun start(timeInMilli: Long) {
        val interval = if (mTimeConfig.showMillisecond) 10 else 1000
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


    private fun updateTime(millisUntilFinished: Long) {
        val day = (millisUntilFinished / (1000 * 60 * 60 * 24)).toInt()
        val hour =
            if (mTimeConfig.showDay) (millisUntilFinished / (1000 * 60 * 60 * 24) / (1000 * 60 * 60)).toInt()
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
