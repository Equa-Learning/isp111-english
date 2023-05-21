package com.example.isp111english.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.isp111english.R

class ChooseVersionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_version)
    }

    fun goToLocal(view: View){
        var intent = Intent(this@ChooseVersionActivity, KlausActivity::class.java)
        startActivity(intent)
        //finish()
    }

    fun goToNetwork(view: View){
        var intent = Intent(this@ChooseVersionActivity, KlausRetrofitActivity::class.java)
        startActivity(intent)
        //finish()
    }
    fun onBackPressed(view: View) {
        finish()
    }
}