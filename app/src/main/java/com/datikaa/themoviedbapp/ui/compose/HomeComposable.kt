package com.datikaa.themoviedbapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.datikaa.themoviedbapp.ImagesBaseUrl
import com.datikaa.themoviedbapp.PicSizeW500
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.ui.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeComposable(viewModel: HomeViewModel) {
    val upcomingMovies by viewModel.upcomingMovies.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Title") }, modifier = Modifier.statusBarsPadding())
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() + 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(it)
        ) {
            items(upcomingMovies) { movie ->
                HomeItemComposable(upcomingMovie = movie)
            }
        }
    }
}

@Composable
fun HomeItemComposable(upcomingMovie: UpcomingMovie) {
    Card(modifier = Modifier.aspectRatio(1.77f)) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImagesBaseUrl + PicSizeW500 + upcomingMovie.backdrop_path,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.pic_loading_placeholder),
                modifier = Modifier.fillMaxWidth(),
            )
            Surface(
                modifier = Modifier.align(Alignment.TopStart),
                color = Color.Black.copy(alpha = 0.5f),
            ) {
                Text(
                    color = Color.White,
                    text = upcomingMovie.title ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
            Surface(
                modifier = Modifier.align(Alignment.BottomEnd),
                color = Color.Black.copy(alpha = 0.5f),
            ) {
                Text(
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    text = upcomingMovie.id.toString(),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}