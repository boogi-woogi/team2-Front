package com.example.infrastudy

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object APIServiceImp {
    private const val BASE_URL = "http://dev-api-team2.openinfra-kr.org/"
    //"http://10.0.2.2"
    //"http://133.186.223.119:80"

    //val okHttpClient=OkHttpClient().newBuilder().addInterceptor(AuthInterceptor()).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        //    .client(okHttpClient)
        .build()

    val loginInterface = retrofit.create(LoginInterface::class.java)
    val joinInterface = retrofit.create(JoinInterface::class.java)
    val makePostInterface= retrofit.create(MakePostInterface::class.java)
    val getPostInterface =retrofit.create(GetPostInterface::class.java)
    val imageInterface= retrofit.create(ImageInterface::class.java)
    val deleteInterface= retrofit.create(DeleteInterface::class.java)
    val getUserInterface=retrofit.create(GetUserInterface::class.java)
}

