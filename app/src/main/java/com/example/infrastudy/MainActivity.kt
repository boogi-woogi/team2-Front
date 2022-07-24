package com.example.infrastudy

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infrastudy.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.File


class MainActivity : AppCompatActivity() {
    var PICK_IMAGE=1111
    lateinit var binding: ActivityMainBinding
    lateinit var myadapter: Adapter
    lateinit var cur_user: String
    var data = ArrayList<GetPostResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //게시글을 작성하려면 현재 유저를 알고 있어야하니까.
//        cur_user = getIntent()?.getSerializableExtra("cur_user") as String
        cur_user="testing"
    }

    override fun onResume() {
        super.onResume()
        initData()
        initLayout()
    }

    private fun initData() {
        Log.i("cur_user", cur_user)
        val i=getIntent()
        cur_user=i.getSerializableExtra("cur_user") as String
        var response_GetPostRequest: Response<ArrayList<GetPostResponse>?>
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                response_GetPostRequest = APIServiceImp.getPostInterface.GetPostRequest()
                Log.i(response_GetPostRequest.toString(), "ffffffffff")
            }
            Log.i("데이터", response_GetPostRequest.body().toString())
            response_GetPostRequest.body()?.let { data.addAll(it) }
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
                response_GetPostRequest.body()?.let { it1 -> data.add(it1.get(data.size)) }
                Log.i("데이터", data.toString())
                myadapter.notifyDataSetChanged()
                Log.i("데이터", "반영완료")
            }
        }
    }

    private fun initLayout() {
//        Log.i("데이터", response_GetPostRequest.body()!!.toString())
        myadapter = Adapter(data)
        myadapter.clickeListener = object : Adapter.OnItemClickListener {
            override fun Clicked(item: GetPostResponse) {
                val i = Intent(this@MainActivity, PostActivity::class.java)
                i.putExtra("post", item)
                startActivity(i)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerView.adapter = myadapter

        binding.button2.setOnClickListener {
            val i = Intent(this, WriteActivity::class.java)
            i.putExtra("cur_user", cur_user)
            activityResultLauncher.launch(i)
        }
//        binding.uploadBtn.setOnClickListener {
//            openGallery()
//        }
    }


//
//    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        val mimeTypes = arrayOf("image/jpeg", "image/png")
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//        startActivityForResult(intent, PICK_IMAGE)
//    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
//            if (data == null) {
//                return
//            }
//            val selectedImage: Uri? = data.data
//            binding.test.setImageURI(selectedImage)
//
//            val mimeType=getMimeType(selectedImage.toString())
//
//            val originalName = selectedImage!!.lastPathSegment
//            val fileName = selectedImage!!.lastPathSegment
//            val path = getRealPathFromURI(selectedImage)
//            Log.i(path, "path")
//            Log.i(mimeType, "mimetype")
//
//        }
//    }

//    @SuppressLint("Range")
//    fun getRealPathFromURI(contentUri: Uri?): String? {
//        val proj = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
//        cursor!!.moveToNext()
//        val path = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.MediaColumns.DATA))
//        val uri = Uri.fromFile(File(path))
//        cursor!!.close()
//        return path
//    }
//
//
//    private fun getMimeType(uri: String): String? {
//        var type: String? = null
//        val extension = MimeTypeMap.getFileExtensionFromUrl(uri)
//        if (extension != null) {
//            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
//        }
//        return type
//    }

}


//    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intent)
//
//        if (requestCode == 102 && resultCode == Activity.RESULT_OK){
//            var currentImageURL = intent?.data
//            // Base64 인코딩부분
//            val ins: InputStream? = currentImageURL?.let {
//                applicationContext.contentResolver.openInputStream(
//                    it
//                )
//            }
//            val img: Bitmap = BitmapFactory.decodeStream(ins)
//            ins?.close()
//            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
//            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
//            val outStream = ByteArrayOutputStream()
//            val res: Resources = resources
//            var profileImageBase64 = Base64.encodeToString(byteArray, NO_WRAP)
//            // 여기까지 인코딩 끝
//
//            // 이미지 뷰에 선택한 이미지 출력
//            val imageview: ImageView = findViewById(id.pet_image)
//            imageview.setImageURI(currentImageURL)
//            try {
//                //이미지 선택 후 처리
//            }catch (e: Exception){
//                e.printStackTrace()
//            }
//        } else{
//            Log.d("ActivityResult", "something wrong")
//        }
//    }


