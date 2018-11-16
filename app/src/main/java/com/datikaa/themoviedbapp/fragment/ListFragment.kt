package com.datikaa.themoviedbapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.TheMovieDbApplication
import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.api.service.TheMovieDbService
import com.datikaa.themoviedbapp.base.BaseFragment
import com.datikaa.themoviedbapp.common.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ListFragment : BaseFragment() {

    private lateinit var searchedFor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchedFor = it.getString(Companion.ARG_SEARCHED_FOR, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView_searchedFor.text = searchedFor
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TheMovieDbApi.theMovieDbService.getMovie(searchedFor)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movie -> textView_searchedFor.text = movie.title }

//        // TODO this shit doesnt belong to here just experimenting
//        val callResponse = TheMovieDbApi.theMovieDbService.getMovie(searchedFor)
//        callResponse.enqueue(object: Callback<Movie> {
//            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
//                textView_searchedFor.text = response.body()?.title
//            }
//
//            override fun onFailure(call: Call<Movie>, t: Throwable) {
//                textView_searchedFor.text = "Fail"
//            }
//        })
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