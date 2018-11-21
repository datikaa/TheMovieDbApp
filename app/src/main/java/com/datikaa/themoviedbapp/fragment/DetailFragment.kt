package com.datikaa.themoviedbapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.AutoTransition
import com.datikaa.themoviedbapp.PicSizeW500
import com.datikaa.themoviedbapp.PicassoBaseUrl
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.api.model.GetMovieResponse
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.base.BaseFragment
import com.datikaa.themoviedbapp.common.inflate
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_list.*

class DetailFragment : BaseFragment() {

    private lateinit var searchedFor: String
    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchedFor = it.getString(ARG_SEARCHED_FOR, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView_movieTitle.text = searchedFor

        sharedElementEnterTransition = AutoTransition().apply {
            duration = 750
        }
        sharedElementReturnTransition= AutoTransition().apply {
            duration = 750
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        disposable.add(TheMovieDbApi.theMovieDbService.getMovie(searchedFor)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movie -> movieResponse(movie) })

//        // TODO this shit doesnt belong to here just experimenting
//        val callResponse = TheMovieDbApi.theMovieDbService.getMovie(searchedFor)
//        callResponse.enqueue(object: Callback<GetMovieResponse> {
//            override fun onResponse(call: Call<GetMovieResponse>, response: Response<GetMovieResponse>) {
//                textView_searchedFor.text = response.body()?.title
//            }
//
//            override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
//                textView_searchedFor.text = "Fail"
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun movieResponse(getMovieResponse: GetMovieResponse) {
        textView_movieTitle.text = getMovieResponse.title
        Picasso.get().load(PicassoBaseUrl + PicSizeW500 + getMovieResponse.backdrop_path).into(imageView_background)
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