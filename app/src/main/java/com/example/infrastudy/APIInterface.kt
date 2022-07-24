package com.example.infrastudy

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.math.BigInteger



interface LoginInterface {
    @FormUrlEncoded
    @POST("/users/login")
    suspend fun postLoginRequest(
        @Field("userid") userid:String,
        @Field("userpw") userpw:String,
    ) : Response<LoginResponse>
}


interface JoinInterface{
    @FormUrlEncoded
    @POST("/users")
    suspend fun postJoinRequest(
        @Field("userid") userid:String,
        @Field("userpw") userpw:String,
        @Field("username") username:String
    ) : Response<JoinResponse>
}


interface MakePostInterface{
    @FormUrlEncoded
    @POST("/board")
    suspend fun MakePostRequest(
        @Field("userid") userid:String,
        @Field("title") title:String,
        @Field("content") content:String,
        @Field("imageSrc") imageSrc:String
    ):Response<MakePostResponse>
}

interface GetPostInterface{

    @GET("/board/list")
    suspend fun GetPostRequest(
    ):Response<ArrayList<GetPostResponse>?>
}

interface DeleteInterface{
    @DELETE("/board/{board_id}")
    suspend fun DeleteRequest(
        @Path("board_id") _id:Int
    ):Response<DeleteResponse>
}

interface ImageInterface{
    @Multipart
    @POST("/images")
    fun sendImg(
        @Part file:MultipartBody.Part
    ):Call<imgaeResponse>
}