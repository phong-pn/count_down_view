package com.phongpn.countdown

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            }
            .start(300000)
    }
}