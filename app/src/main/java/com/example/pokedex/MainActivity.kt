package com.example.pokedex

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

var pokeTestListSize = 100
val pokeTestList = ArrayList<PokemonItem>(pokeTestListSize)

class MainActivity : AppCompatActivity() {
    private var SPLASH_TIME = 3000 //This is 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        popListVisible(pokeTestList) // Cheaty way to make sure the user diesnt load into a blank screen 


        Handler().postDelayed({ //Do any action here. Now we are moving to next page
            val mySuperIntent = Intent(this, ActivityHome::class.java)
            startActivity(mySuperIntent)
            popListRest(pokeTestList, pokeTestListSize)

            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
            finish()
        }, SPLASH_TIME.toLong())



    }


}

/**Pre: list must be an empty arrayList holding PokemonItem in all lowerCase
 * Post:  Adds the first n amount of pokemon
 */
fun popListVisible(list: ArrayList<PokemonItem>){
    for (n in 1..7)
    {  //Initializes the Lists with the information of first 7 pokemon
        list.add(PokemonItem(n))
    }
}
fun popListRest(list: ArrayList<PokemonItem>, size: Int){
    for (n in 8..size)
    {  //Initializes the list with the information of each pokemon
        list.add(PokemonItem(n))
    }
}

/**Pre: oldString must be a string in all lowerCase
 * Post:  Returns newstring with the first letter capitalized
 */

fun cap( oldString: String?): String?{
    if(oldString==null) return null
    if(oldString[0].isUpperCase()) return oldString

    var newString=""
    newString+= oldString[0].toUpperCase()
    for(i in 1 until oldString.length){
        newString+=oldString[i]
    }
    return newString
}





