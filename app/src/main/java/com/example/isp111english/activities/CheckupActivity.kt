package com.example.isp111english.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.isp111english.R
import com.example.isp111english.adapters.QuestionsAdapter
import com.example.isp111english.application.UserService
import com.example.isp111english.data.CheckupQuestions

class CheckupActivity : AppCompatActivity() {

    lateinit var questionsList: RecyclerView
    var userService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkup)

        userService.Init(this)

        questionsList = findViewById(R.id.checkup_questions)
        questionsList.adapter = QuestionsAdapter(this, CheckupQuestions().list)
    }

    fun onBackPressed(view: View) {
        finish()
    }

    fun goToCheckupResults(view: View) {
        val correctAnswers = CheckupQuestions().list.map { x -> x.answer }
        var goodPointsCounter = 0;
        for (i in 0 until questionsList.childCount) {
            val questionView = questionsList.getChildAt(i)
            val editText = questionView.findViewById<EditText>(R.id.answer)
            if (editText.text.toString().lowercase() == correctAnswers[i])
                goodPointsCounter += 1
        }
        userService.goodPoints = goodPointsCounter

        var intent = Intent(this@CheckupActivity, CheckupResultsActivity::class.java)
        startActivity(intent)
    }

    fun goToLesson(view: View) {
//        var intent = Intent(this@CheckupActivity, LessonActivity::class.java)
//        startActivity(intent)
        finish()
    }
}