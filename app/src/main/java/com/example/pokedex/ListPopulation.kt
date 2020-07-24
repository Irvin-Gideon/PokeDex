package com.example.pokedex

import android.graphics.Bitmap
import kotlinx.coroutines.*

/** This class will handle the creation and population of Lists containing the pokemon names, types
 * and images.
 **/
class ListPopulation(numOfItems: Int){
    var namesOfPokemon = mutableListOf<String?>()
    var typesOfPokemon = mutableListOf<String?>()
    var imgOfPokemon = mutableListOf<Bitmap?>()
    private val countOfItems: Int = numOfItems // #NOTE: there are 807 pokemon in api

    init {
        for (n in 1..numOfItems) {  //Initializes the Lists with the information of each pokemon
            namesOfPokemon.add(getPokemonName(n))
            typesOfPokemon.add(getPokemonType(n))
            imgOfPokemon.add(getPokemonSprite(n))
        }

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
        return parseJSONObj(species,"name")
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the type in the JSON data
     */
    private fun getPokemonType(pokemonID: Int): String? {
        var s=""
        runBlocking{//Launches the task with runBlocking so it executes sequentially
            s=downloaderTask("https://pokeapi.co/api/v2/pokemon/$pokemonID")
        }
        val typesArr = parseJSONObj(s,"types")
        val types= parseJSONArr(typesArr,0)
        return parseJSONObj(parseJSONObj(types,"type"),"name")
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




