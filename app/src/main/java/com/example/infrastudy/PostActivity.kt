package com.example.infrastudy

import android.net.Uri
import android.net.Uri.parse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.infrastudy.databinding.ActivityPostBinding
import kotlinx.android.synthetic.main.activity_post.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.HttpCookie.parse
import java.net.URI

class PostActivity : AppCompatActivity() {
    lateinit var binding : ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        val i=getIntent()
        val cur_user=i?.getSerializableExtra("cur_user") as String
        var cur_post=i?.getSerializableExtra("item") as GetPostResponse
        val cur_image=cur_post.imageSrc
        binding.postname.text=cur_post.title
        binding.author.text=cur_user
        binding.maintext.text=cur_post.content
        if(cur_image!="x") binding.imageView2.setImageURI(Uri.parse(cur_image))

    }
}