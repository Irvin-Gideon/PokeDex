package com.example.pokedex

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ListView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
class PokemonList : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listView: ListView
    //TODO remove testing
    var testList= ListPopulation (10)

    private var name = mutableListOf<String?>()
    private val type1 = mutableListOf<String?>()
    private val type2 = mutableListOf<String?>()
    private val image = mutableListOf<Bitmap?>()


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

        //TODO remove testing
        type1.addAll(testList.types1OfPokemon)
        type2.addAll(testList.types2OfPokemon)

        //sets image List to that of the ListPopulation instance's
        image.addAll(testList.imgOfPokemon)
        //sets name List to that of the ListPopulation instance's
        name.addAll(testList.namesOfPokemon)

        listView = view.findViewById(R.id.listView)
        val customListView = activity?.let { CustomListView(it,name, type1,type2, image) }
        listView.adapter = customListView

        // Launches fragment to proper Pokemon information page
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
               // view.findNavController().navigate(R.id.action_pokemonList_to_pokemonInfoDisplay);

                //Creates an Intent object by giving the context and the class of next activity to be opened
                // A passive data structure holding an abstract description of an action to be performed.
                val mIntent: Intent? = Intent(this.context, DetailActivity::class.java)
                mIntent?.putExtra("pokemonSprite", image[position]) //Attach the key value pair using putExtra to this intent
                startActivity(mIntent)
            }

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

private fun <E> MutableList<E>.addAll(elements: MutableList<E?>) {
    for(n in elements) if (n != null) add(n)

}
