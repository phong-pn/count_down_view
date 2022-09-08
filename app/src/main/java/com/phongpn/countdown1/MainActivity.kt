package com.phongpn.countdown1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phongpn.countdown.config.Modifier
import com.phongpn.countdown.countdown.CountDownView
import com.phongpn.countdown.util.dp
import com.proxglobal.countdown1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<CountDownView>(R.id.countdown).apply {
            config(modifier = Modifier(timePaddingLeft = 8.dp, timePaddingRight = 8.dp))
            start(3_600_00)
        }
    }
}