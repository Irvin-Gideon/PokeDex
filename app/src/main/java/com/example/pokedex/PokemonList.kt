package com.example.pokedex

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.Activity.DetailActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonList : Fragment(), CustomRecyclerAdapter.OnItemClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerVw: RecyclerView

    //TODO remove testing

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflates the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_pokemon_list, container, false)


        recyclerVw = view.findViewById(R.id.recyclerView)
        val customAdapter = CustomRecyclerAdapter(pokeTestList, this)
        recyclerVw.adapter = customAdapter
        recyclerVw.layoutManager = LinearLayoutManager(context)
        recyclerVw.setHasFixedSize(true)


        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu,inflater)
        inflater.inflate(R.menu.searchmenu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Navigates to search fragment on clicking menu item
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        //Creates an Intent object by giving the context and the class of next activity to be opened
        // A passive data structure holding an abstract description of an action to be performed.
        val clickedItem: ReworkedPokemonItem = pokeTestList[position]
        val mIntent: Intent? = Intent(this.context, DetailActivity::class.java)
        mIntent?.putExtra("pokemonSprite", clickedItem.pokemonSprite) //Attach the key value pair using putExtra to this intent
        mIntent?.putExtra("pokemonType", clickedItem.pokemonType1)
        mIntent?.putExtra("pokemonType2", clickedItem.pokemonType2)
        startActivity(mIntent)
}
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment pokemonList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


