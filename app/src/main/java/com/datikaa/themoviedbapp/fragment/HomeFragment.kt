package com.datikaa.themoviedbapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.base.BaseFragment
import com.datikaa.themoviedbapp.common.inflate
import com.datikaa.themoviedbapp.ui.MovieListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_home)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_search.setOnClickListener {
            val bundle = DetailFragment.getBundle(inputLayout_searchedFor.editText?.text.toString())
            view.findNavController().navigate(R.id.openListFragmentFromHome, bundle)
        }

        recycler_view.adapter = MovieListAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TheMovieDbApi.theMovieDbService.getLatest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response -> response.results ?: listOf() }
            .subscribe { list -> notifyAdapter(list) }
    }

    private fun notifyAdapter(movieList: List<UpcomingMovie>) {
        val adapter = recycler_view.adapter as MovieListAdapter?
        adapter?.list = movieList
        adapter?.notifyDataSetChanged()
    }
}
