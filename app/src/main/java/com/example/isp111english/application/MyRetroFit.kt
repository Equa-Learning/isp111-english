package com.example.isp111english.application

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class myRetroFit {
    val BASE_URL:String = "http://mskko2021.mad.hakta.pro/api/";

    fun getRetrofit():Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}