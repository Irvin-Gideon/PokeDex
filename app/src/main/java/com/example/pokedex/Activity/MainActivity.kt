package com.example.pokedex.Activity

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R
import com.example.pokedex.imageDownloaderTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val mContext: Context? = null

    fun getContext(): Context? {
        return mContext
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            val inf: Bitmap? =
                imageDownloaderTask("https://images.theconversation.com/files/336212/original/file-20200519-152292-3nomu2.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=1200.0&fit=crop")
            Log.i("test",inf.toString())

         //imageView.setImageBitmap(inf)

            //image.toMutableList().add(inf)


        }


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;




    }


}





