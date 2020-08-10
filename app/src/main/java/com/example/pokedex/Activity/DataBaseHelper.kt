package com.example.pokedex.Activity

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pokedex.*


class DataBaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "PokemonList.db", null, 1) {
    val POKEMON_TABLE: String = "POKEMON_TABLE"
    val POKEMON_NAME: String = "POKEMON_NAME"
    val POKEMON_TYPEONE: String = "POKEMON_TYPEONE"
    val POKEMON_TYPETWO: String = "POKEMON_TYPETWO"
    val POKEMON_SPRITE_URL: String = "POKEMON_SPRITE_URL"
    val COLUMN_ID: String = "ID INTEGER PRIMARY KEY AUTOINCREMENT,"


    //This will be called the first time a database is accessed
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement =
            "CREATE TABLE $POKEMON_TABLE ($COLUMN_ID $POKEMON_NAME TEXT, $POKEMON_TYPEONE TEXT, " +
                    "$POKEMON_TYPETWO TEXT, $POKEMON_SPRITE_URL TEXT)"

        db?.execSQL(createTableStatement)
    }

    //This is called if the database version number changes
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL( "DROP TABLE IF EXISTS " + POKEMON_TABLE )
        // other calls like onCreate if necessary
        onCreate(db)
    }

    private suspend fun addOne(pokemonID: Int): Boolean {
        val webPage: String?
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        webPage = downloaderTask("https://pokeapi.co/api/v2/pokemon/$pokemonID") //Reduced to one API call

        cv.put(POKEMON_NAME, getPokemonName(webPage))
        cv.put(POKEMON_TYPEONE, getPokemonType1(webPage))
        cv.put(POKEMON_TYPETWO, getPokemonType2(webPage))
        cv.put(POKEMON_SPRITE_URL, getPokemonSpriteURL(webPage))
        val insert = db.insert(POKEMON_TABLE, null, cv)

        if (insert == -1L) {
            return false
        }
        return true
    }
    suspend fun addList(size: Int){
        for(n in 1..size){
            addOne(n)
        }
    }

    //Create a method that will SELECT all records from the table
    fun getEveryOne() : ArrayList<ReworkedPokemonItem> {

        val returnList: ArrayList<ReworkedPokemonItem> = ArrayList()

        //Get data from the database
        val queryString = "SELECT * FROM $POKEMON_TABLE"

        val db: SQLiteDatabase = this.readableDatabase

        val cursor: Cursor = db.rawQuery(queryString, null)

        if(cursor.moveToFirst())
        {
            //Loop through cursor(result set) and create new PokemonItem
            //and put them in the return list.

            do {
                val pokeName: String = cursor.getString(1)
                val pokeTypeOne: String = cursor.getString(2)
                val pokeTypeTwo: String? = cursor.getString(3) ?: null // Null Check
                val pokeSpriteUrl: String = cursor.getString(4)

                val newPokemonItem = ReworkedPokemonItem(pokeName,pokeTypeOne,pokeTypeTwo,pokeSpriteUrl)
                returnList.add(newPokemonItem)

            }while (cursor.moveToNext())


        }
        else{
            //In failure nothing is added to the list
        }

        cursor.close()
        db.close()
        return returnList
    }

    fun tableExists(): Boolean {
        val db: SQLiteDatabase = this.readableDatabase

        val queryString = ("SELECT * FROM $POKEMON_TABLE")
        val cursor: Cursor = db.rawQuery(queryString, null)
        if (cursor.moveToFirst()){//if there is an initial row
            db.close()
            cursor.close()
            return true
        }
        db.close()
        cursor.close()
        return false // if table is empty
    }



    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post: Parses through the JSON file to find the name in the JSON data
     */
    private suspend fun getPokemonName(link: String?): String? {
        val species = parseJSONObj(link,"species")
        return cap(parseJSONObj(species,"name"))
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the first pokemon type in the JSON data
     */
    private suspend fun getPokemonType1(link: String?): String? {
        val typesArr = parseJSONObj(link,"types")
        val types= parseJSONArr(typesArr,0)
        val firstType= parseJSONObj(types,"type")
        return cap(parseJSONObj(firstType,"name")) //Retrieves first type and name of that type
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the type in the JSON data
     */

    private suspend fun getPokemonType2(link: String?): String? {
        val typesArr = parseJSONObj(link,"types")

        val types= parseJSONArr(typesArr,1) //if there is no second type
        val firstType= parseJSONObj(types,"type")
        return cap(parseJSONObj(firstType,"name")) //Retrieves first type and name of that type
    }

    /**Pre: pokemonID must be an integer that specifies which pokemon the function call has to retrieve
     * the data from. ID's range from 1-807.
     * Post:  Parses through the JSON file to find the type in the JSON data
     */
    private suspend fun getPokemonSpriteURL(link: String?): String? {
        val typesArr = parseJSONObj(link,"sprites")
        return parseJSONObj(typesArr, "front_default")
    }
}

