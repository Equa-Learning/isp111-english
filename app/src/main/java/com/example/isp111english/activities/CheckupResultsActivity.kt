package com.example.isp111english.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.isp111english.R
import com.example.isp111english.application.UserService
import com.example.isp111english.data.CheckupQuestions
import com.example.isp111english.databinding.ActivityCheckupResultsBinding


class CheckupResultsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheckupResultsBinding
    lateinit var name: TextView

    var userService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckupResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userService.Init(this)

        binding.apply {
            dear.setText(userService.userName +" " + userService.userLastName+", пришло время узнать результаты!")
            goodpoints.setText("Правильных ответов: "+userService.goodPoints.toString()+" из "+CheckupQuestions().list.size)
            if (userService.goodPoints >= CheckupQuestions().list.size) {
                decision.setText("Тест пройден!")
            } else {
                decision.setText("Тест НЕ пройден! Попробуй ещё раз!")
            }
        }
    }

    fun onBackPressed(view: View) {
        finish()
    }

    fun goToChoose(view: View) {
        var intent = Intent(this@CheckupResultsActivity, ChooseLessonActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun goToCheckup(view: View) {
        var intent = Intent(this@CheckupResultsActivity, CheckupActivity::class.java)
        startActivity(intent)
        finish()
    }
}