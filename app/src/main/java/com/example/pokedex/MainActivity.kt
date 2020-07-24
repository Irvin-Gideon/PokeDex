package com.example.pokedex

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_layout.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            val inf: Bitmap? =  imageDownloaderTask ("https://images.theconversation.com/files/336212/original/file-20200519-152292-3nomu2.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=1200.0&fit=crop")
            Log.i("test",inf.toString())

         //imageView.setImageBitmap(inf)

            //image.toMutableList().add(inf)



        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;




    }


}





