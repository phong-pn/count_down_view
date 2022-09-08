package com.phongpn.countdown.util

import android.util.Log
import com.phongpn.countdown.BuildConfig

private const val TAG = "LOG_DEBUG"
fun Any.logd(tag: String = TAG) {
    if (!BuildConfig.DEBUG) return
    if (this is String) {
        Log.d(tag, this)
    } else {
        Log.d(tag, this.toString())
    }
}