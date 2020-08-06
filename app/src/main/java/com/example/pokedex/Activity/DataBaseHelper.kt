package com.example.pokedex.Activity

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.pokedex.PokemonItem
import com.example.pokedex.ReworkedPokemonItem
import com.example.pokedex.downloaderTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataBaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "PokemonList.db", null, 1) {
    val POKEMON_TABLE: String = "POKEMON_TABLE"
    val POKEMON_NAME: String = "POKEMON_NAME"
    val POKEMON_TYPEONE: String = "POKEMON_TYPEONE"
    val POKEMON_TYPETWO: String = "POKEMON_TYPETWO"
    val POKEMON_SPRITE_URL: String = "POKEMON_SPRITE_URL"
    val COLUMN_ID: String = "ID INTEGER PRIMARY KEY AUTOINCREMENT, "


    //This will be called the first time a database is accessed
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement: String = "CREATE TABLE " + POKEMON_TABLE + " (" + COLUMN_ID +
                POKEMON_NAME + " TEXT, " + POKEMON_TYPEONE + " TEXT, " +
                POKEMON_TYPETWO + " TEXT, " + POKEMON_SPRITE_URL + " TEXT)"
        Log.i("Bob", createTableStatement)
        db?.execSQL(createTableStatement)
    }

    //This is called if the database version number changes
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addOne(pokemonItem: PokemonItem): Boolean {


        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(POKEMON_NAME, pokemonItem.pokemonName)
        cv.put(POKEMON_TYPEONE, pokemonItem.pokemonType1)
        cv.put(POKEMON_TYPETWO, pokemonItem.pokemonType2)
        val pokemonSpriteUrl = POKEMON_SPRITE_URL
        cv.put(pokemonSpriteUrl, pokemonItem.spriteURL)


        val insert = db.insert(POKEMON_TABLE, null, cv)

        if (insert == -1L) {
            return false
        }
        return true
    }

}

