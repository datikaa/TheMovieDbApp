package com.datikaa.themoviedbapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.base.BaseFragment
import com.google.android.material.textfield.TextInputLayout

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button_search)
        val input = view.findViewById<TextInputLayout>(R.id.inputLayout_searchedFor)
        button.setOnClickListener {
            val bundle = ListFragment.getBundle(input.editText?.text.toString())
            view.findNavController().navigate(R.id.openListFragmentFromHome, bundle)
        }
    }
}