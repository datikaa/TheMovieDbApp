package com.datikaa.themoviedbapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private lateinit var upcomingMovies: MutableLiveData<List<UpcomingMovie>>

    fun getUpcomingMovies(): LiveData<List<UpcomingMovie>> {
        if (!::upcomingMovies.isInitialized) {
            upcomingMovies = MutableLiveData()
            loadUsers()
        }
        return upcomingMovies
    }

    private fun loadUsers() {
        // this is still not belong here, but better than nothing...
        // really should start to implement repositories, but not yet necessary
        TheMovieDbApi.theMovieDbService.getLatest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response -> response.results ?: listOf() }
            .subscribe { list -> upcomingMovies.value = list }
    }
}