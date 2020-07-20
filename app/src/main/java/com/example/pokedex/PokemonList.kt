package com.example.pokedex

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ListView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonList : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listView: ListView
    private val name = mutableListOf("Die 1", "Die 2", "Die 3", "Die 4", "Die 5", "Die 6")
    private val desc = mutableListOf("1","2", "3", "4", "5", "6")
    private val image = mutableListOf(R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6,R.drawable.empty_dice)


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

        listView = view.findViewById(R.id.listView)
        val customListView = activity?.let { CustomListView(it,name, desc, image) }
        listView.adapter = customListView

        // Launches fragment to proper Pokemon information page
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                view.findNavController().navigate(R.id.action_pokemonList_to_pokemonInfoDisplay)
            }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu,inflater)
        inflater?.inflate(R.menu.searchmenu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Navigates to search fragment on clicking menu item
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
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