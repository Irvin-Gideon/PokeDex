package com.example.pokedex

import android.widget.ArrayAdapter
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CustomListView : ArrayAdapter<String>{
    private var name : List<String> = listOf()
    private var desc : List<String> = listOf()
    private var image : List<Int> = listOf()
    private var context: Activity

    constructor(context: Activity, name: List<String>, desc: List<String>, image: List<Int>) : super(context,R.layout.activity_main,name){
        this.context = context
            this.name = name
            this.desc = desc
            this.image = image
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var r : View? = convertView
        val vwHolder: ViewHolder?

        if (r==null)
        {
            val layoutInflater : LayoutInflater = context.layoutInflater
            r = layoutInflater.inflate(R.layout.list_layout, null,true)
            vwHolder = ViewHolder(r)
            r.tag = vwHolder
        }
        else{
            vwHolder = r.tag as ViewHolder?

        }

        if (vwHolder != null) {
            vwHolder.imageVw?.setImageResource(image[position])
        }

        if (vwHolder != null) {
            vwHolder.textVw1?.text = name[position]
        }

        if (vwHolder != null) {
            vwHolder.textVw2?.text = desc[position]
        }




        return r as View
    }

    class ViewHolder{

        var textVw1: TextView? = null
        var textVw2: TextView? = null
        var imageVw: ImageView? = null

        constructor(view: View)  {
            textVw1 = view.findViewById(R.id.tvPokemonType)
            textVw2 = view.findViewById(R.id.tvPokemonDesc)
            imageVw = view.findViewById(R.id.imageView)
        }
    }

}
