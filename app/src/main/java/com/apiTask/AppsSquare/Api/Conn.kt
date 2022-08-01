package com.apiTask.AppsSquare.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Conn {

    var calls = Retrofit
        .Builder()
        .baseUrl("https://android-training.appssquare.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Calls::class.java)
}