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
class PokemonList : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerVw: RecyclerView

    //TODO remove testing

    private val pokeTestList = ArrayList<PokemonItem>(7)
    private fun popList(){
        for (n in 1..7)
        {  //Initializes the Lists with the information of each pokemon
            pokeTestList.add(PokemonItem(n))
        }
    }

//    private fun popList(lo: Int, hi: Int){
//        if(hi>lo){
//            val mid: Int = (hi+lo)/2
//            popList(lo,mid)
//            popList(mid+1,hi)
//            for(n in lo..hi){
//                pokeTestList.add(PokemonItem(n))
//            }
//        }
//    }
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

        popList()


        recyclerVw = view.findViewById(R.id.recyclerView)
        val customAdapter = CustomRecyclerAdapter(pokeTestList)
        recyclerVw.adapter = customAdapter
        recyclerVw.layoutManager = LinearLayoutManager(context)
        recyclerVw.setHasFixedSize(true)
        // Launches fragment to proper Pokemon information page
//        recyclerVw.onItemClickListener (
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//               // view.findNavController().navigate(R.id.action_pokemonList_to_pokemonInfoDisplay);
//
//                //Creates an Intent object by giving the context and the class of next activity to be opened
//                // A passive data structure holding an abstract description of an action to be performed.
//                val mIntent: Intent? = Intent(this.context, DetailActivity::class.java)
//                mIntent?.putExtra("pokemonSprite", image[position]) //Attach the key value pair using putExtra to this intent
//                mIntent?.putExtra("pokemonType", type1[position])
//                mIntent?.putExtra("pokemonType2", type2[position])
//                startActivity(mIntent)
//            }
//        )
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
