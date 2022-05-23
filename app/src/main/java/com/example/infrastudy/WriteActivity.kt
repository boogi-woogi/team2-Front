package com.example.infrastudy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.infrastudy.databinding.ActivityWriteBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class WriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityWriteBinding
    lateinit var cur_user: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cur_user = getIntent()?.getSerializableExtra("cur_user") as String
        init()
    }

    private fun init() {
        binding.button.setOnClickListener {
            var response_MakePost: Response<MakePostResponse>
            val title = binding.postnameW.text.toString()
            val content = binding.maintextW.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    response_MakePost = APIServiceImp.makePostInterface.MakePostRequest(
                        userid = cur_user,
                        title = title,
                        content = content
                    )
                    if (response_MakePost != null) {
                        val i=Intent()
                        i.putExtra("additional_data", PostData(cur_user, title, content))
                        Log.i("message", response_MakePost.body().toString())
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
//                    else {
//                        Log.i("message", "making post failed")
//                        setResult(Activity.RESULT_CANCELED)
//                        finish()
//                    }
                }

            }
        }

    }
}
