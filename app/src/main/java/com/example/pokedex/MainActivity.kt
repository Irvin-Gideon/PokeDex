package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //test
        val test = DownloaderTask()
        TextView.text = test.execute("https://pokeapi.co/api/v2/pokemon/ditto").get()
    }
}