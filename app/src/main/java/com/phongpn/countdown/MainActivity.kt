package com.phongpn.countdown

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.phongpn.countdown.countdown.CountDownView
import com.phongpn.countdown.util.dp


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<CountDownView>(R.id.count_down)
            .text {
                padding(l = 0.dp, r = 0.dp)
                margin(l = 5.dp, r = 5.dp)
                offset(t = 3.dp.toInt(), b = 3.dp.toInt())
                viewConfig(
                    color = Color.WHITE
                )
//                background(cornerRadius = 5.dp, color = Color.WHITE)
            }
            .suffix {
                margin(all = 5.dp)
            }
            .display {
                showDay = true
//                showMillisecond = true
            }
            .apply {
            }
            .start(1662829200000 - System.currentTimeMillis())
    }
}