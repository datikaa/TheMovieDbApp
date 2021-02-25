package com.datikaa.themoviedbapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository : MovieRepository,
) : ViewModel() {

    private val _upcomingMovies = MutableSharedFlow<MutableList<UpcomingMovie>>()
    val upcomingMovies: Flow<MutableList<UpcomingMovie>> = _upcomingMovies

    init {
        fetchUpcomingMovies()
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            val popularMovies = repository.getUpcomingMovies()
            _upcomingMovies.emit(popularMovies!!)
        }
    }
}