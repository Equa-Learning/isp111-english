package com.example.isp111english.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.isp111english.R
import com.example.isp111english.application.UserService

class ChooseLessonActivity : AppCompatActivity() {

    lateinit var name: TextView
    var userService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_lesson)

        userService.Init(this)

        name=findViewById(R.id.dear)
        name.setText("Привет, " + userService.userFullName )
    }

    fun onBackPressed(view: View) {
        finish()
    }

    fun goToCheckup(view: View) {
        var intent = Intent(this@ChooseLessonActivity, CheckupActivity::class.java)
        startActivity(intent)
    }
    fun goToLesson(view: View) {
        var intent = Intent(this@ChooseLessonActivity, LessonActivity::class.java)
        startActivity(intent)
        //finish()
    }
}