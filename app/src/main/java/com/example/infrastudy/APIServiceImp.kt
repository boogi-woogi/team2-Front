package com.example.infrastudy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIServiceImp {
    private const val BASE_URL = "http://10.0.2.2:3000"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginInterface = retrofit.create(LoginInterface::class.java)
    val joinInterface = retrofit.create(JoinInterface::class.java)
    val makePostInterface= retrofit.create(MakePostInterface::class.java)
}

