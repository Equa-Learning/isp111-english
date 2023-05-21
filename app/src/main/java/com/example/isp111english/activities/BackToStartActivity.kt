package com.example.isp111english.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.isp111english.R

class BackToStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_to_start)
    }

    fun goBack(view: View) {
        var intent = Intent(this@BackToStartActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}