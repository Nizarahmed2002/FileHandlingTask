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


                    }
                }

            imgSetter.setOnClickListener{



                pickImageLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )


            }
            submit.setOnClickListener{

                fileCreation(name.text.toString())

                userData(name.text.toString(),mail.text.toString(),phone.text.toString().toInt(),imgPath)







            }




        }
    }

    private fun fileCreation(fileName:String) {
        fileDir  = Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_DOCUMENTS}/File/$fileName")
        fileDir.mkdirs()

    }
    private fun writeFile(data :UserData) {


        try {


            val filename = File(fileDir, "profile.txt")
            val fileContents = data
            FileOutputStream(filename).use { OutputStream ->
                OutputStream.write(Gson().toJson(data).toByteArray())
                Log.i("status", "onCreate: ")
                OutputStream.close()

            }
        } catch (e: Exception) {
            Log.i("stauts", "onCreate: ${e.printStackTrace()}")
        }
    }
}