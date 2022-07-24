package com.example.infrastudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.widget.Toast
import com.example.infrastudy.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    companion object {
        const val TAG = "LoginActivty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postInformation()
        binding.userid.setText("fantasy7772")
        binding.userpw.setText("123123k")
        binding.username.setText("jinuk")
    }

    private fun postInformation() {
        binding.apply {
            button3.setOnClickListener {
                var response_Join: Response<JoinResponse>
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        response_Join = APIServiceImp.joinInterface.postJoinRequest(
                            userid = userid.text.toString(),
                            userpw = userpw.text.toString(),
                            username = username.text.toString()
                        )
                        if (response_Join != null) {
                            Log.i("message", "join success")

                        } else {
                            Log.i("message", "join failed")
                        }
                    }
                    if(response_Join!=null){
                        val i= Intent(this@LoginActivity, MainActivity::class.java)
                        i.putExtra("cur_user", userid.text.toString())
                        startActivity(i)
                    }
                }
            }
            button4.setOnClickListener {
                var response_Login: Response<LoginResponse>
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        response_Login = APIServiceImp.loginInterface.postLoginRequest(
                            userid = userid.text.toString(),
                            userpw = userpw.text.toString()
                        )
                        if (response_Login != null) {
                            Log.i("message", "login success")
                        } else {
                            Log.i("message", "login failed")
                        }
                    }
                    if(response_Login!=null){
                        val i= Intent(this@LoginActivity, MainActivity::class.java)
                        i.putExtra("cur_user", userid.text.toString())
                        startActivity(i)
                    }
                }
            }
        }
    }
}