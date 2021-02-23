package com.datikaa.themoviedbapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import coil.load
import com.datikaa.themoviedbapp.PicSizeW500
import com.datikaa.themoviedbapp.ImagesBaseUrl
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.base.BaseFragment
import com.datikaa.themoviedbapp.common.inflate
import com.datikaa.themoviedbapp.ui.home.HomeFragment

import kotlinx.android.synthetic.main.fragment_list.*

class DetailFragment : BaseFragment() {

    private lateinit var searchedFor: String
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchedFor = it.getString(ARG_SEARCHED_FOR, "")
        }

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        viewModel.fetchMovie(searchedFor)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = container?.inflate(R.layout.fragment_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView_movieTitle.text = searchedFor

        viewModel.movie.observe(this.viewLifecycleOwner, { movie ->
            textView_movieTitle.text = movie.title
            imageView_background.load(ImagesBaseUrl + PicSizeW500 + movie.backdrop_path)
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