package com.example.infrastudy

import android.content.Intent
import android.net.Uri
import android.net.Uri.parse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.infrastudy.databinding.ActivityPostBinding
import kotlinx.android.synthetic.main.activity_post.*
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
        Log.i("cur_user",cur_user)
        var cur_post=i?.getSerializableExtra("item") as GetPostResponse
        val cur_image=cur_post.imageSrc
        binding.postname.text=cur_post.title
        binding.author.text=cur_user
        binding.maintext.text=cur_post.content
        if(cur_image!="x") {
            Glide.with(this)
                .load(cur_image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.imageView2)
            //binding.imageView2.setImageURI(Uri.parse(cur_image))
        }
        binding.deletebtn.setOnClickListener {
            var response_delete:Response<DeleteResponse>
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO){
                    response_delete=APIServiceImp.deleteInterface.DeleteRequest(
                        _id=cur_post.postid
                    )
                    if(response_delete!=null){
                        val i= Intent(this@PostActivity,MainActivity::class.java)
                        i.putExtra("cur_user",cur_user)
                        startActivity(i)
                        Log.i("del","del success")

                    }
                    else{
                        Log.i("del","del fail")
                    }
                }
            }

        }

    }
}