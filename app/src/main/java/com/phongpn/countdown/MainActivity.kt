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
        findViewById<CountDownView>(R.id.countdown)
            .modifier {
                timePaddingRight = 10.dp
                timePaddingLeft = 10.dp
            }
            .text {
                suffixColor = Color.RED
                timeFont = ResourcesCompat.getFont(this@MainActivity, R.font.poppins_medium)
            }
            .time {
                showDay = true
//                showMillisecond = true
            }
            .start(1662829200000 - System.currentTimeMillis())
    }
}