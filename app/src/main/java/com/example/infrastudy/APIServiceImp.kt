package com.example.infrastudy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIServiceImp {
    private const val BASE_URL = "http://133.186.223.119:80"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginInterface = retrofit.create(LoginInterface::class.java)
    val joinInterface = retrofit.create(JoinInterface::class.java)
    val makePostInterface= retrofit.create(MakePostInterface::class.java)
    val getPostInterface =retrofit.create(GetPostInterface::class.java)
}

