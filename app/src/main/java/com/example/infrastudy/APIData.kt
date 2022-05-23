package com.example.infrastudy

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigInteger

data class JoinData (
    @SerializedName("userid") val id:String,
    @SerializedName("password") val pw:String,
    @SerializedName("username") val name:String
)

data class LoginData(
    @SerializedName("userid") val id:String,
    @SerializedName("password") val pw:String
)

data class PostData(
    @SerializedName("userid") val id:String,
    @SerializedName("title") val title:String,
    @SerializedName("content") val content:String
) : Serializable

data class LoginResponse(
    @SerializedName("message") val msg:String
)

data class JoinResponse(
    @SerializedName("message") val msg:String
)

data class MakePostResponse(
    @SerializedName("message") val msg:String
)

data class GetPostResponse(
    @SerializedName("userid") val userid:String,
    @SerializedName("postid") val postid: Int,
    @SerializedName("title") val title:String,
    @SerializedName("content") val content:String
):Serializable