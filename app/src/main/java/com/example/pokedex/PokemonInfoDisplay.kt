package com.example.pokedex

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var pokemonInfoNameView: TextView
lateinit var pokemonInfoImageView: ImageView
lateinit var pokemonInfoType1View: TextView
lateinit var pokemonInfoType2View: TextView

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonInfoDisplay.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonInfoDisplay : Fragment(R.layout.fragment_pokemon_info_display) {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_pokemon_info_display, container, false)

        pokemonInfoNameView = view.findViewById(R.id.pokemonInfoName)
        pokemonInfoImageView= view.findViewById(R.id.pokemonInfoImage)
        pokemonInfoType1View =view.findViewById(R.id.pokemonInfoType1)
        pokemonInfoType2View =view.findViewById(R.id.pokemonInfoType2)

        if(this.arguments?.getString("pokemonURL") != null) { // Executes code if bundle info was pulled from Database
            var pokeName = (this.arguments?.getString("pokemonName"))
            if (pokeName == "farfetchd") { //  for the abnormal cases
                pokeName = "farfetch'd"
            }
            if (pokeName == "mr-mime") {// for the abnormal cases
                pokeName = "mr. Mime"
            }

            val pokeType1 = (this.arguments?.getString("pokemonType1"))
            val pokeType2 = (this.arguments?.getString("pokemonType2"))
            val spriteURL = (this.arguments?.getString("pokemonURL"))

            CoroutineScope(Dispatchers.Main).launch {
                val sprite: Bitmap? = imageDownloaderTask(spriteURL)
                pokemonInfoNameView.text = (cap(pokeName))

                pokemonInfoImageView.setImageBitmap(sprite)
                pokemonInfoType1View.text = pokeType1
                pokemonInfoType2View.text = pokeType2
                if (pokemonInfoType2View.text == null) {
                    pokemonInfoType2View.setBackgroundColor(Color.WHITE)
                }

            }
        }
        else{ // Default code if the user input was not found in Database

           var entry= this.arguments?.getString("pokemonName") // Retrieves the bundle pair passed from the other fragment



            CoroutineScope(Dispatchers.Main).launch {
                val webpage = downloaderTask("https://pokeapi.co/api/v2/pokemon/$entry")

                val pokemonSprite = getPokemonSprite(webpage)
                pokemonInfoImageView.scaleX = .5F
                pokemonInfoImageView.scaleY = .5F

                pokemonInfoImageView.setImageBitmap(pokemonSprite)
                pokemonInfoType1View.text = getPokemonType1(webpage)
                pokemonInfoType2View.text = getPokemonType2(webpage)
                if (pokemonInfoType2View.text == null) {
                    pokemonInfoType1View.x= 20F
                    pokemonInfoType2View.setBackgroundColor(Color.WHITE)
                }


                //animate on image entry
                pokemonInfoImageView.animate().scaleXBy(.8F).scaleYBy(.8F).duration = 300

                if (entry == "farfetchd") { //  for the abnormal cases
                    entry = "farfetch'd"
                }
                if (entry == "mr-mime") {// for the abnormal cases
                    entry = "mr. Mime"
                }
                pokemonInfoNameView.text = cap(entry)

            }
        }
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PokemonInfoDisplay.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonInfoDisplay().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}

/**Pre: pokemonName must be an name that specifies which pokemon the function call has to retrieve
 * the data from. If 'pokemonName' is an invalid string it will return an error message
 * Post:  Parses through the JSON file to find the type in the JSON data
 */
 suspend fun getPokemonType1( webp: String): String? {

    val typesArr = parseJSONObj(webp,"types")
    val types= parseJSONArr(typesArr,0)
    val firstType= parseJSONObj(types,"type")
    return cap(parseJSONObj(firstType,"name")) //Retrieves first type and name of that type
}

/**Pre: pokemonName must be an name that specifies which pokemon the function call has to retrieve
 * the data from. If 'pokemonName' is an invalid string it will return an error message
 * Post:  Parses through the JSON file to find the type in the JSON data
 */
suspend fun getPokemonType2(webp: String): String? {

    val typesArr = parseJSONObj(webp,"types")
    val types= parseJSONArr(typesArr,1)
    val firstType= parseJSONObj(types,"type")
    return cap(parseJSONObj(firstType,"name")) //Retrieves first type and name of that type
}

/**Pre: pokemonName must be an name that specifies which pokemon the function call has to retrieve
 * the data from. If 'pokemonName' is an invalid string it will return an error message
 * Post:  Parses through the JSON file to find the type in the JSON data
 */
suspend fun getPokemonSprite(webp: String): Bitmap? {
    return try {

        val typesArr = parseJSONObj(webp, "sprites")
        val spriteURL = parseJSONObj(typesArr, "front_default")
        val spriteImage: Bitmap? =spriteURL?.let { imageDownloaderTask(it) }

        spriteImage
    }catch (e: Exception){
        null
    }
}
