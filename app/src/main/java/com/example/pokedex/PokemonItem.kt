package com.example.pokedex

import android.graphics.Bitmap
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


class PokemonItem(pokemonID: Int) {
    var pokemonSprite : Bitmap? = null
    var pokemonName: String? =null
    var pokemonType1: String? = null
    var pokemonType2: String? = null


    init{
        pokemonName= getPokemonName(pokemonID)
        pokemonSprite=getPokemonSprite(pokemonID)
        pokemonType1=getPokemonType1(pokemonID)
        pokemonType2=getPokemonType2(pokemonID)
//        runBlocking {
//            val v1 = async{getPokemonName(pokemonID)}
//            val v2 = async{getPokemonType1(pokemonID)}
//            val v3 = async{getPokemonType2(pokemonID)}
//            val v4 = async{getPokemonSprite(pokemonID)}
//
//
//            pokemonName = v1.await()
//            pokemonType1 = v2.await()
//            pokemonType2 = v3.await()
//            pokemonSprite=v4.await()
//
//        }
    }
    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post: Parses through the JSON file to find the name in the JSON data
     */
    private fun getPokemonName(pokemonID: Int): String? {
        var s=""
        runBlocking{//Launches the task with runBlocking so it executes sequentially
            s=downloaderTask("https://pokeapi.co/api/v2/pokemon/$pokemonID")
        }
        val species = parseJSONObj(s,"species")
        return cap(parseJSONObj(species,"name"))
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the type in the JSON data
     */
    private fun getPokemonType1(pokemonID: Int): String? {
        var s=""
        runBlocking{//Launches the task with runBlocking so it executes sequentially
            s=downloaderTask("https://pokeapi.co/api/v2/pokemon/$pokemonID")
        }
        val typesArr = parseJSONObj(s,"types")
        val types= parseJSONArr(typesArr,0)
        val firstType= parseJSONObj(types,"type")
        return cap(parseJSONObj(firstType,"name")) //Retrieves first type and name of that type
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the type in the JSON data
     */

    private fun getPokemonType2(pokemonID: Int): String? {

        var s=""
        runBlocking{//Launches the task with runBlocking so it executes sequentially
            s=downloaderTask("https://pokeapi.co/api/v2/pokemon/$pokemonID")
        }
        val typesArr = parseJSONObj(s,"types")

        val types= parseJSONArr(typesArr,1) ?: return null //if there is no second type


        val firstType= parseJSONObj(types,"type")
        return cap(parseJSONObj(firstType,"name")) //Retrieves first type and name of that type
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the type in the JSON data
     */
    private fun getPokemonSprite(pokemonID: Int): Bitmap? {
        var s=""
        runBlocking{//Launches the task with runBlocking so it executes sequentially
            s=downloaderTask("https://pokeapi.co/api/v2/pokemon/$pokemonID")

        }
        val typesArr = parseJSONObj(s,"sprites")
        val spriteURL = parseJSONObj(typesArr, "front_default")

        var spriteImage: Bitmap? = null
        runBlocking {  spriteImage = spriteURL?.let { imageDownloaderTask(it) } }

        return spriteImage
    }

}