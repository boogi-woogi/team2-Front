package com.example.infrastudy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infrastudy.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myadapter: Adapter
    lateinit var cur_user: String
    var data = ArrayList<GetPostResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //게시글을 작성하려면 현재 유저를 알고 있어야하니까.
        cur_user = getIntent()?.getSerializableExtra("cur_user") as String
        initData()
//        initLayout()
    }

    private fun initData() {
        Log.i("cur_user", cur_user)
        var response_GetPostRequest: Response<ArrayList<GetPostResponse>?>
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                response_GetPostRequest = APIServiceImp.getPostInterface.GetPostRequest()
            }
            Log.i("데이터", response_GetPostRequest.body()!!.toString())
            data.addAll(response_GetPostRequest.body()!!)
            myadapter = Adapter(data)
            myadapter.clickeListener = object : Adapter.OnItemClickListener {
                override fun Clicked(item: GetPostResponse) {
                    val i = Intent(this@MainActivity, PostActivity::class.java)
                    i.putExtra(("cur_user"), cur_user)
                    i.putExtra("item", item)
                    startActivity(i)
                }
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL, false
            )
            binding.recyclerView.adapter = myadapter

            binding.button2.setOnClickListener {
                val i = Intent(this@MainActivity, WriteActivity::class.java)
                i.putExtra("cur_user", cur_user)
                activityResultLauncher.launch(i)
            }
        }


    }

    val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            var response_GetPostRequest: Response<ArrayList<GetPostResponse>?>
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    response_GetPostRequest = APIServiceImp.getPostInterface.GetPostRequest()
                }
                data.add(response_GetPostRequest.body()!!.get(data.size))
                Log.i("데이터", data.toString())
                myadapter.notifyDataSetChanged()
                Log.i("데이터", "반영완료")
            }
        }
    }

//    private fun initLayout() {
//        Log.i("데이터", response_GetPostRequest.body()!!.toString())
//        myadapter = Adapter(data)
//        myadapter.clickeListener = object : Adapter.OnItemClickListener {
//            override fun Clicked(item: GetPostResponse) {
//                val i = Intent(this@MainActivity, PostActivity::class.java)
//                i.putExtra("post", item)
//                startActivity(i)
//            }
//        }
//        binding.recyclerView.layoutManager = LinearLayoutManager(
//            this,
//            LinearLayoutManager.VERTICAL, false
//        )
//        binding.recyclerView.adapter = myadapter
//
//        binding.button2.setOnClickListener {
//            val i = Intent(this, WriteActivity::class.java)
//            i.putExtra("cur_user", cur_user)
//            activityResultLauncher.launch(i)
//        }
//    }
    }



