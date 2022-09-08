package com.phongpn.countdown.config

data class TimeConfig(
    val showDay: Boolean = false,
    val showHour: Boolean = true,
    val showMinute: Boolean = true,
    val showSecond: Boolean = true,
    val showMillisecond: Boolean = false
)