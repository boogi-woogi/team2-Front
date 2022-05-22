package com.example.infrastudy

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginInterface {
    @FormUrlEncoded
    @POST("/users/login")
    suspend fun postLoginRequest(
        @Field("userid") userid:String,
        @Field("password") userpw:String,
    ) : Response<LoginResponse>
}


interface JoinInterface{
    @FormUrlEncoded
    @POST("/users")
    suspend fun postJoinRequest(
        @Field("userid") userid:String,
        @Field("password") userpw:String,
        @Field("username") username:String
    ) : Response<JoinResponse>
}


interface MakePostInterface{
    @FormUrlEncoded
    @POST("/board")
    suspend fun MakePostRequest(
        @Field("userid") userid:String,
        @Field("title") title:String,
        @Field("content") content:String
    ):Response<MakePostResponse>
}