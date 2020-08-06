package com.example.pokedex

import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReworkedPokemonItem(var pokemonName: String?, var pokemonType1: String?, var pokemonType2: String?,var spriteURL: String? ) {
    var pokemonSprite : Bitmap? = null

    init{
        CoroutineScope(Dispatchers.Main).launch {
            pokemonSprite = imageDownloaderTask(spriteURL)
        }

    }

//idea is to make it so that we can pull name, type, etc.. straight from database class. Then we save it in the database
    //pull it from db when needed and create use that data to create these objects and use them to populate recylcer
}