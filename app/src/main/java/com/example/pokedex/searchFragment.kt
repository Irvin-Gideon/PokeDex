package com.example.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [searchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class searchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var searchButton : ImageButton
    lateinit var editSearchBar : EditText

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
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_search, container, false)
        searchButton = view.findViewById(R.id.searchButton)
        editSearchBar = view.findViewById(R.id.editSearchBar)

        //Sends information to display fragment in order to show pokemon information
        searchButton.setOnClickListener(){
            CoroutineScope(Dispatchers.Main).launch {
                if (editSearchBar.text.toString().isBlank()) {
                    // If user input is Blank, it will prompt the user to input a value
                    editSearchBar.text.clear()
                    editSearchBar.hint = "Input valid Pokémon Name."
                } else {
                    var entry = editSearchBar.text.toString().toLowerCase()
                    entry = entry.replace("\\s".toRegex(), "-") // Replaces whitepace with dashes (needed for API call)



                    if (dataBaseHelper.tableExists()) { // Executes code if pokemon was able to be pulled from Database
                        val pokemonDB = dataBaseHelper.searchDB(entry)
                        val bundle: Bundle = bundleOf(
                            "pokemonName" to pokemonDB?.pokemonName,
                            "pokemonType1" to pokemonDB?.pokemonType1,
                            "pokemonType2" to pokemonDB?.pokemonType2,
                            "pokemonURL" to pokemonDB?.spriteURL
                        )
                        view.findNavController()
                            .navigate(R.id.action_searchFragment_to_pokemonInfoDisplay, bundle)
                    } else {


                        if ("$entry " !in allPokemon) { //Asserts that user input is a valid pokemon choice found in the list of allPokemon
                            Toast.makeText(context, "Invalid Value ", Toast.LENGTH_SHORT).show()
                        } else {

                            if (entry == "farfetch'd") { //  for the abnormal cases
                                entry = "farfetchd"
                            }
                            if (entry == "mr. mime" || entry == "mr mime") { // for the abnormal cases
                                entry = "mr-mime"
                            }
                            //Sends whatever user typed into the editText in a bundle pair to the next fragment
                            val bundle: Bundle = bundleOf("pokemonName" to entry)
                            view.findNavController()
                                .navigate(R.id.action_searchFragment_to_pokemonInfoDisplay, bundle)
                        }
                    }
                }
            }

        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment searchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            searchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}