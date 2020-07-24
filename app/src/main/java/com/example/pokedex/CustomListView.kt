package com.example.pokedex

import android.widget.ArrayAdapter
import android.app.Activity
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * CustomListView class allows us to use the ArrayAdapter to return a view for wach object
 * in a collection of data objects that we provide which can be using in conjunction with interfaces
 * such as ListView
 */
class CustomListView : ArrayAdapter<String>{
    //List that reflects the elements in the similarly named objects in
    //our MainActivity file
    private var name  = mutableListOf<String?>()
    private var desc =   mutableListOf<String>()
    private var image = mutableListOf<Bitmap?>()
    private var context: Activity

    //CustomListVew constructor
    constructor(context: Activity, name: MutableList<String?>, desc: MutableList<String>, image: MutableList<Bitmap?>) : super(context,R.layout.activity_main,name){
        this.context = context
            this.name = name
            this.desc = desc
            this.image = image
    }

    /**
     * Gives us a view that displays the data at a specified position in the data set
     * through
     * @param position : The position of the item within our adapter's data set of the item we want to view
     * @param convertView View?: The old view to reuse
     * @param parent ViewGroup: The parent that this view will be attached to
     * @return View
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row  : View? = convertView
        val vwHolder: ViewHolder?

        //Runs if view value is currently null
        if (row == null)
        {
            val layoutInflater : LayoutInflater = context.layoutInflater

            //Allows us to specify the view and prevent attachment to the root
            row  = layoutInflater.inflate(R.layout.list_layout, null,true)
            vwHolder = ViewHolder(row )
            row .tag = vwHolder
        }
        else{
            vwHolder = row .tag as ViewHolder?

        }

        if (vwHolder != null) {
            vwHolder.imageVw?.setImageBitmap(image[position])
            vwHolder.textVw1?.text = name[position]
            vwHolder.textVw2?.text = desc[position]


        }

        return row  as View
    }

    //ViewHolder class allows us to assign view to specific ids
    class ViewHolder{

        var textVw1: TextView? = null
        var textVw2: TextView? = null
        var imageVw: ImageView? = null

        constructor(view: View)  {
            textVw1 = view.findViewById(R.id.tvPokemonName)
            textVw2 = view.findViewById(R.id.tvPokemonDesc)
            imageVw = view.findViewById(R.id.imageView)
        }

    }

}
