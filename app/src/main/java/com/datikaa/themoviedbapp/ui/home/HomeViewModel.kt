package com.datikaa.themoviedbapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository : MovieRepository = MovieRepository()

    val upcomingMovies = MutableLiveData<MutableList<UpcomingMovie>>()

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            val popularMovies = repository.getUpcomingMovies()
            upcomingMovies.postValue(popularMovies)
        }
    }
}