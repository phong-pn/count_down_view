package com.phongpn.countdown.config

data class TimeConfig(
    var showDay: Boolean = false,
    var showHour: Boolean = true,
    var showMinute: Boolean = true,
    var showSecond: Boolean = true,
    var showMillisecond: Boolean = false
)