package com.example.pokedex

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val name = listOf("Die 1", "Die 2", "Die 3", "Die 4", "Die 5", "Die 6")
    private val desc = listOf("1","2", "3", "4", "5", "6")
    private val image = listOf(R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch{ //All functionalities must be executed in this scope as it is the main thread
            ///val tv = findViewById<TextView>(R.id.textView)
         // tv.text = downloaderTask("https://pokeapi.co/api/v2/pokemon/ditto")

//            //My test
//            val pokemon = listOf(downloaderTask("https://pokeapi.co/api/v2/pokemon/"))
//           val pokemonDesc = getJSONInfo("name", pokemon.toString())


        }

        listView =  findViewById(R.id.listview)

        val customListView = CustomListView(this,name, desc, image)
        listView.adapter = customListView
    }
}