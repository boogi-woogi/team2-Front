package com.example.infrastudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.infrastudy.databinding.ActivityPostBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

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
        val post_title=binding.postname.text.toString()
        val post_author=cur_user
        val post_content=binding.maintext.text.toString()

    }
}