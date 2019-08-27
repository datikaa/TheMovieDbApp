package com.datikaa.themoviedbapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.base.BaseFragment
import com.datikaa.themoviedbapp.common.inflate
import com.datikaa.themoviedbapp.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private val adapter = MovieListAdapter()
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.fetchUpcomingMovies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_home)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_search.setOnClickListener {
            val input = inputLayout_searchedFor.editText?.text.toString()
            if (input.isNotEmpty()) {
                val bundle =
                    DetailFragment.getBundle(inputLayout_searchedFor.editText?.text.toString())
                view.findNavController().navigate(R.id.openDetailFragmentFromHome, bundle)
            }
        }

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.addItemDecoration(HomeListItemDecoration())

        viewModel.upcomingMovies.observe(this, Observer<List<UpcomingMovie>> { upcomingMovies ->
            adapter.dispatchChanges(upcomingMovies)
        })
    }

    override fun onDestroyView() {
        viewModel.upcomingMovies.removeObservers(this)
        super.onDestroyView()
    }
}
