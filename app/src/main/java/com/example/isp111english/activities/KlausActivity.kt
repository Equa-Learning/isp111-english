package com.example.isp111english.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.isp111english.R
import com.example.isp111english.adapters.Actor_adapter
import com.example.isp111english.adapters.Frame_adapter
import com.example.isp111english.data.KlausActors
import com.example.isp111english.data.KlausFrames

class KlausActivity : AppCompatActivity() {

    lateinit var name: TextView
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klaus)
        val framesView:RecyclerView= findViewById(R.id.klaus_frames)
        framesView.adapter = Frame_adapter(this, KlausFrames().list)
        val actorsView:RecyclerView= findViewById(R.id.klaus_actors)
        actorsView.adapter = Actor_adapter(this, KlausActors().list)

        pref = getSharedPreferences("TABLEE", MODE_PRIVATE)
        name=findViewById(R.id.username)
        name.setText(pref?.getString("userName", "") + " " + pref?.getString("userLastName", ""))

    }

    fun onBackPressed(view: View) {
        finish()
    }
}