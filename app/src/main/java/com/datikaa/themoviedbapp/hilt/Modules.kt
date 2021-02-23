package com.datikaa.themoviedbapp.hilt

import com.datikaa.themoviedbapp.api.service.TheMovieDbApi
import com.datikaa.themoviedbapp.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FooModule {

    @Provides
    @Singleton
    fun provideTmdbApi() : TheMovieDbApi {
        return TheMovieDbApi()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(theMovieDbApi: TheMovieDbApi) : MovieRepository {
        return MovieRepository(theMovieDbApi)
    }
}
