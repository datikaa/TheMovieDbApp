package com.datikaa.themoviedbapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.base.BaseFragment

// the fragment initialization parameters
private const val ARG_SEARCHED_FOR = "arg_searched"

class ListFragment : BaseFragment() {

    private lateinit var searchedFor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchedFor = it.getString(ARG_SEARCHED_FOR, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.textView_searchedFor)
        textView.text = searchedFor
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param searchedFor What the user searched for.
         * @return A new instance of fragment BaseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(searchedFor: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SEARCHED_FOR, searchedFor)
                }
            }

        @JvmStatic
        fun getBundle(searchedFor: String) =
            Bundle().apply {
                putString(ARG_SEARCHED_FOR, searchedFor)
            }
    }
}