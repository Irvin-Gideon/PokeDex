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
//        fun toString(): String {
//            return "$pokemonName : $pokemonType1 and $pokemonType2 "
//        }

    }

}