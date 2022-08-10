package com.datikaa.themoviedbapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
    val itemsState = viewModel.upcomingMovies.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        topBar = {
            SmallTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(text = "Title") })
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
            items(itemsState.value) { movies ->
                HomeItemComposable(upcomingMovie = movies)
            }
        }
    }
}

@Composable
fun HomeItemComposable(upcomingMovie: UpcomingMovie) {
    Card(modifier = Modifier.aspectRatio(1.77f)) {

        AsyncImage(
            model = ImagesBaseUrl + PicSizeW500 + upcomingMovie.backdrop_path,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.pic_loading_placeholder),
            modifier = Modifier.fillMaxWidth(),
        )
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (title, id) = createRefs()

            Text(
                style = TextStyle(color = Color.White, fontSize = 32.sp),
                text = upcomingMovie.title ?: "",
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top, margin = 0.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                    }
            )
            Text(
                color = Color.White,
                text = upcomingMovie.id.toString(),
                modifier = Modifier
                    .constrainAs(id) {
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
            )
        }
    }
}