package com.datikaa.themoviedbapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.repository.MovieRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : MovieRepository,
) : ViewModel() {

    val upcomingMovies = MutableLiveData<MutableList<UpcomingMovie>>()

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            val popularMovies = repository.getUpcomingMovies()
            upcomingMovies.postValue(popularMovies!!)
        }
    }
}