package com.datikaa.themoviedbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.themoviedbapp.api.model.Movie
import com.datikaa.themoviedbapp.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel constructor(
    private val repository : MovieRepository,
): ViewModel() {

    val movie = MutableLiveData<Movie>()

    fun fetchMovie(id: String) {
        viewModelScope.launch {
            val m = repository.getMovie(id)
            movie.postValue(m!!)
        }
    }
}