package com.example.isp111english.interfaces

import com.example.isp111english.data.model.data
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("feelings")
    fun getImages():Call<data>
}