package com.example.isp111english.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.isp111english.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var timer= object : CountDownTimer(2000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                //
            }

            override fun onFinish() {
                var intent = Intent(this@MainActivity, SigninActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        timer.start()
    }

}