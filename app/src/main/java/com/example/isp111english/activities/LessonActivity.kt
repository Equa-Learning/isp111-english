package com.example.isp111english.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.isp111english.R

class LessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

    }

    fun onBackPressed(view: View) {
        finish()
    }

    fun goToCheckup(view: View) {
        val intent = Intent(this@LessonActivity, CheckupActivity::class.java)
        startActivity(intent)
    }
}