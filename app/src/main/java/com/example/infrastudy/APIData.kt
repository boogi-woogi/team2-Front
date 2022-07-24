package com.example.infrastudy

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
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
    @SerializedName("content") val content:String,
    @SerializedName("imageSrc") val imageSrc:String
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
    @SerializedName("_id") val postid: Int,
    @SerializedName("board_title") val title:String,
    @SerializedName("board_content") val content:String,
    @SerializedName("image_src") val imageSrc:String,
    @SerializedName("user_id") val userid:String?
):Serializable

data class imgaeResponse(
    @SerializedName("message") val message:String,
    @SerializedName("image_src") val image_Src:String
)

data class DeleteResponse(
    @SerializedName("message") val msg:String
)
