package com.example.pokedex

import android.widget.ArrayAdapter
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * CustomListView class allows us to use the ArrayAdapter to return a view for wach object
 * in a collection of data objects that we provide which can be using in conjunction with interfaces
 * such as ListView
 */
class CustomListView//CustomListVew constructor
    (
    private var context: Activity,//List that reflects the elements in the similarly named objects in
    //our MainActivity file
    //TODO: add support for multiple pokemon types
    private var name: MutableList<String?>,
    private var type1: MutableList<String?>,
    private var type2: MutableList<String?>,
    private var image: MutableList<Bitmap?>
) : RecyclerView.Adapter<CustomListView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.fragment_pokemon_list, parent, false)
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageVw?.setImageBitmap(image[position] as Bitmap)
        holder.textVw1?.text = name[position]
        holder.textVw2?.text = type1[position]
        holder.textVw3?.text = type2[position]

        if(type2[position]==null){
            holder.textVw3?.setBackgroundColor(Color.WHITE)
        }

    }


    override fun getItemCount(): Int {
        return this.name.size
    }

    //ViewHolder class allows us to assign view to specific ids
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textVw1: TextView? = null
        var textVw2: TextView? = null
        var textVw3: TextView? = null
        var imageVw: ImageView? = null

        init {

            textVw1 = view.findViewById(R.id.tvPokemonName)
            textVw2 = view.findViewById(R.id.tvPokemonType1)
            textVw3 = view.findViewById(R.id.tvPokemonType2)
            imageVw = view.findViewById(R.id.imageView)
        }
    }


    /**
     * Gives us a view that displays the data at a specified position in the data set
     * through
     * @param position : The position of the item within our adapter's data set of the item we want to view
     * @param convertView View?: The old view to reuse
     * @param parent ViewGroup: The parent that this view will be attached to
     * @return View
     */
    fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
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
            vwHolder.textVw2?.text = type1[position]
            vwHolder.textVw3?.text = type2[position]

            if(type2[position]==null){
                vwHolder.textVw3?.setBackgroundColor(Color.WHITE)
            }
        }


        return row  as View
    }








}
