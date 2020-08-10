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
    private val PokemonList: List<ReworkedPokemonItem>,
    private val listener: OnItemClickListener //List that reflects the elements in the similarly named objects in
) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: ReworkedPokemonItem = PokemonList[position]
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
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var textVw1: TextView? = view.tvPokemonName
        var textVw2: TextView? = view.tvPokemonType1
        var textVw3: TextView? = view.tvPokemonType2
        var imageVw: ImageView? = view.imageView

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

    }
    interface OnItemClickListener{ //abstract class that is used in our list fragment to inherit from
        fun onItemClick(position: Int)
    }


}
