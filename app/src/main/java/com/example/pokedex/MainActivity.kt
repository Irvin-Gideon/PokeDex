package com.example.pokedex

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Activity.DataBaseHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

var pokeTestListSize = 100
val pokeTestList = ArrayList<ReworkedPokemonItem>(pokeTestListSize)
lateinit var dataBaseHelper: DataBaseHelper

class MainActivity : AppCompatActivity() {
    private var SPLASH_TIME = 5000 //This is 5 seconds

    private var imageView : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


         dataBaseHelper= DataBaseHelper(this)
        CoroutineScope(Dispatchers.Main).launch {
            if(!dataBaseHelper.tableExists()) {
                dataBaseHelper.addList(50)
                SPLASH_TIME=9000
            }else{SPLASH_TIME=3000}
            val everyone: ArrayList<ReworkedPokemonItem> = dataBaseHelper.getEveryOne()
            pokeTestList.addAll(everyone)
        }



        //TODO Cleanup old
        //popListVisible(pokeTestList) // Cheaty way to make sure the user diesnt load into a blank screen
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        window.setBackgroundDrawable(null)
        initializeView()
        animateLogo()
        goToMainActivity()



    }

    private fun initializeView()
    {
        imageView = findViewById(R.id.splashLogo)
    }


    private fun animateLogo(){
//        val fadingInAnimation: Animation = AnimationUtils.loadAnimation(this,R.anim.fade_in)
//        fadingInAnimation.duration = SPLASH_TIME.toLong()
//        imageView?.startAnimation(fadingInAnimation)
        imageView?.alpha=0F
        imageView?.animate()?.rotationBy(1440F)?.duration=SPLASH_TIME/4L
        imageView?.animate()?.alpha(1F)?.duration=SPLASH_TIME.toLong()
    }

    private fun goToMainActivity()
    {
        Handler().postDelayed({ //Do any action here. Now we are moving to next page
            val mySuperIntent = Intent(this, ActivityHome::class.java)
            startActivity(mySuperIntent)
            //TODO cleanup old
            //popListRest(pokeTestList, pokeTestList.size+1,pokeTestList.size+21)
            //popListRest(pokeTestList, pokeTestList.size+1,pokeTestList.size+21)


            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
            finish()
        }, SPLASH_TIME.toLong())

    }
}



/**Pre: list must be an empty arrayList holding PokemonItem in all lowerCase
 * Post:  Adds the first n amount of pokemon
 */
fun popListVisible(list: ArrayList<PokemonItem>){
    for (n in 1..7) {  //Initializes the Lists with the information of first 7 pokemon
        list.add(PokemonItem(n))
    }
}
fun popListRest(list: ArrayList<PokemonItem>, start: Int,end: Int){
    for (n in start..end)
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





