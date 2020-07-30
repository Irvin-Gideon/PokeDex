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
import kotlinx.android.synthetic.main.list_layout.view.*

/**
 * CustomListView class allows us to use the ArrayAdapter to return a view for wach object
 * in a collection of data objects that we provide which can be using in conjunction with interfaces
 * such as ListView
 */
class CustomRecyclerAdapter//Custom recyclerView adapter constructor
    (
    private val PokemonList: List<PokemonItem>//List that reflects the elements in the similarly named objects in
) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: PokemonItem = PokemonList[position]
        holder.imageVw?.setImageBitmap(currentItem.pokemonSprite as Bitmap?)
        holder.textVw1?.text = currentItem.pokemonName
        holder.textVw2?.text = currentItem.pokemonType1
        holder.textVw3?.text = currentItem.pokemonType2

        if(currentItem.pokemonType2==null){
            holder.textVw3?.setBackgroundColor(Color.WHITE)
        }else{
            holder.textVw3?.setBackgroundColor(Color.parseColor("#75CFB4FF"))
        }

    }


    override fun getItemCount(): Int {
        return this.PokemonList.size
    }

    //ViewHolder class allows us to assign view to specific ids
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textVw1: TextView? = view.tvPokemonName
        var textVw2: TextView? = view.tvPokemonType1
        var textVw3: TextView? = view.tvPokemonType2
        var imageVw: ImageView? = view.imageView

    }


//    /**
//     * Gives us a view that displays the data at a specified position in the data set
//     * through
//     * @param position : The position of the item within our adapter's data set of the item we want to view
//     * @param convertView View?: The old view to reuse
//     * @param parent ViewGroup: The parent that this view will be attached to
//     * @return View
//     */
//    fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var row  : View? = convertView
//        val vwHolder: ViewHolder?
//
//        //Runs if view value is currently null
//        if (row == null)
//        {
//            val layoutInflater : LayoutInflater = context.layoutInflater
//
//            //Allows us to specify the view and prevent attachment to the root
//            row  = layoutInflater.inflate(R.layout.list_layout, null,true)
//            vwHolder = ViewHolder(row )
//            row .tag = vwHolder
//        }
//        else{
//            vwHolder = row .tag as ViewHolder?
//
//        }
//
//        if (vwHolder != null) {
//            vwHolder.imageVw?.setImageBitmap(image[position])
//            vwHolder.textVw1?.text = name[position]
//            vwHolder.textVw2?.text = type1[position]
//            vwHolder.textVw3?.text = type2[position]
//
//            if(type2[position]==null){
//                vwHolder.textVw3?.setBackgroundColor(Color.WHITE)
//            }
//        }
//
//
//        return row  as View
//    }
//
//






}
