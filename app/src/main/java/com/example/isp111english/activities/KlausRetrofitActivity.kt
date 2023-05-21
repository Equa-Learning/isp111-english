package com.example.isp111english.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.isp111english.R
import com.example.isp111english.adapters.Actor_adapterNetwork
import com.example.isp111english.adapters.Frame_adapter
import com.example.isp111english.application.myRetroFit
import com.example.isp111english.data.KlausFrames
import com.example.isp111english.data.model.data
import com.example.isp111english.interfaces.RetrofitServices
import retrofit2.Call
import retrofit2.Response

class KlausRetrofitActivity : AppCompatActivity() {

    lateinit var actorsView:RecyclerView
    lateinit var name: TextView

    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klaus)


        val framesView:RecyclerView= findViewById(R.id.klaus_frames)
        framesView.adapter = Frame_adapter(this, KlausFrames().list)

        actorsView = findViewById(R.id.klaus_actors)


        val api_retrofit = myRetroFit().getRetrofit().create(RetrofitServices::class.java)
        val retr_call: Call<data> = api_retrofit.getImages()
        retr_call.enqueue(object:retrofit2.Callback<data>{
            override fun onResponse(call: Call<data>, response: Response<data>) {
                if (response.isSuccessful) {
                    actorsView.adapter= response.body()?.let {
                        Actor_adapterNetwork(applicationContext, response.body()!!.data)
                    }
                }
            }

            override fun onFailure(call: Call<data>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        pref = getSharedPreferences("TABLEE", MODE_PRIVATE)
        name = findViewById(R.id.username)
        name.setText(pref?.getString("userName", "") + " " + pref?.getString("userLastName", ""))
    }

    fun onBackPressed(view: View) {
        finish()
    }
}