package com.example.infrastudy

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.infrastudy.databinding.ActivityWriteBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.File

class WriteActivity : AppCompatActivity() {
    var PICK_IMAGE=1111
    var imageSrc:String="x"
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
        binding.imgBtn.setOnClickListener {
            openGallery()
        }
        binding.button.setOnClickListener {
            var response_MakePost: Response<MakePostResponse>
            val title = binding.postnameW.text.toString()
            val content = binding.maintextW.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    if(imageSrc!="x"){
                        response_MakePost = APIServiceImp.makePostInterface.MakePostRequest(
                            userid = cur_user,
                            title = title,
                            content = content,
                            imageSrc = imageSrc!!.toString()
                        )
                    }
                    else{
                        response_MakePost = APIServiceImp.makePostInterface.MakePostRequest(
                            userid = cur_user,
                            title = title,
                            content = content,
                            imageSrc = "x"
                        )
                    }

                    Log.i("fff", cur_user+" "+title+" "+content)
                    if (response_MakePost != null) {
                        val i=Intent()
                        i.putExtra("additional_data", PostData(cur_user, title, content, imageSrc!!))
                        Log.i("message", response_MakePost.body().toString())
                        setResult(Activity.RESULT_OK)
                        finish()
                    }

                }

            }
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(intent, PICK_IMAGE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data == null) {
                return
            }
            val selectedImage: Uri? = data.data
            binding.uploadImage.setImageURI(selectedImage)
            val path = getRealPathFromURI(selectedImage)
            imageSrc=path!!
            Log.i(path, "path")
        }
    }


    @SuppressLint("Range")
    fun getRealPathFromURI(contentUri: Uri?): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
        cursor!!.moveToNext()
        val path = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.MediaColumns.DATA))
        val uri = Uri.fromFile(File(path))
        cursor!!.close()
        return path
    }

}
