package com.example.infrastudy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infrastudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var myadapter:Adapter
    lateinit var cur_user:String
    var data=ArrayList<PostData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //게시글을 작성하려면 현재 유저를 알고 있어야하니까.
        cur_user=getIntent()?.getSerializableExtra("cur_user") as String
        initData()
        initLayout()
    }

    private fun initData(){

    }

    val activityResultLauncher=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode==Activity.RESULT_OK){
//            val additional_data=it.data?.getSerializableExtra("data") as PostData
//            data.add(additional_data)
//            myadapter.notifyDataSetChanged()
        }
    }

    private fun initLayout() {
        myadapter= Adapter(data)
        myadapter.clickeListener=object:Adapter.OnItemClickListener{
            override fun Clicked(item: PostData) {
                val i= Intent(this@MainActivity, PostActivity::class.java)
                i.putExtra("post", item)
                startActivity(i)
            }
        }
        binding.recyclerView.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter=myadapter

        binding.button2.setOnClickListener {
            val i=Intent(this, WriteActivity::class.java)
            i.putExtra("cur_user", cur_user)
            activityResultLauncher.launch(i)
        }
    }


}