package com.example.filehandlingtask

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.service.autofill.UserData
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import coil.load
import com.example.filehandlingtask.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var fileDir : File
    private lateinit var binding  : ActivityMainBinding
    private lateinit var imgPath : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding){




            val pickImageLauncher =
                registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { result ->
                    result?.let { path ->
                        imageView.load(path)
                        imgPath = path
                        Log.i("path", "$imgPath ")


                    }
                }

            RecycleButton.setOnClickListener {

                ReadFile()

            }

            imgSetter.setOnClickListener{



                pickImageLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )


            }
            submit.setOnClickListener{
                Log.i("path", "$imgPath ")





                runBlocking {
                        fileCreation(name.text.toString())
                    }
                GlobalScope.launch(Dispatchers.IO){
                   async {   writeFile(userData(name.text.toString(),mail.text.toString(),phone.text.toString().toInt().toLong(),imgPath.toString()))}.await()
                  async {   Log.i("read", "readFile: ${File(fileDir, "profile.json").readText()}")}.await()

                }











            }




        }
    }

    private fun ReadFile() {

    }

    private fun fileCreation(fileName:String) {
        fileDir  = Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_DOCUMENTS}/File/$fileName")
        fileDir.mkdirs()

    }
    private  fun writeFile(data :userData) {


        try {


            val filename = File(fileDir, "profile.json")
            val fileContents = data
            FileOutputStream(filename).use { OutputStream ->
                OutputStream.write(Gson().toJson(fileContents).toByteArray())
                Log.i("status", "onCreate: ")
                OutputStream.close()

            }
        } catch (e: Exception) {
            Log.i("stauts", "onCreate: ${e.printStackTrace()}")
        }
    }
}