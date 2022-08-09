package com.datikaa.themoviedbapp

import android.app.Application
import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.repository.MovieRepository
import com.datikaa.themoviedbapp.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class TheMovieDbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheMovieDbApplication)
            modules(
                module {
                    singleOf(::MovieRepository)
                    singleOf(::TheMovieDbApi)
                    viewModelOf(::HomeViewModel)
                }
            )
        }
    }
}