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
        }


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    }


}

private fun <E> MutableList<E>.addAll(elements: MutableList<E?>) {
    for(n in elements){
        if (n != null) {
            add(n)
        }
    }
}





