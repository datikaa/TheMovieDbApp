package com.datikaa.themoviedbapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.datikaa.themoviedbapp.PicSizeW500
import com.datikaa.themoviedbapp.PicassoBaseUrl
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.base.BaseFragment
import com.datikaa.themoviedbapp.common.inflate
import com.datikaa.themoviedbapp.ui.home.HomeFragment
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_list.*

class DetailFragment : BaseFragment() {

    private lateinit var searchedFor: String
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchedFor = it.getString(ARG_SEARCHED_FOR, "")
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetchMovie(searchedFor)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = container?.inflate(R.layout.fragment_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView_movieTitle.text = searchedFor

        viewModel.movie.observe(this, Observer<Movie> { movie ->
            textView_movieTitle.text = movie.title
            Picasso.get().load(PicassoBaseUrl + PicSizeW500 + movie.backdrop_path)
                .into(imageView_background)
        })
    }

    override fun onDestroyView() {
        viewModel.movie.removeObservers(this)
        super.onDestroyView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param searchedFor What the user searched for.
         * @return A new instance of fragment BaseFragment.
         */
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

        // the fragment initialization parameters
        private const val ARG_SEARCHED_FOR = "arg_searched"
    }
}