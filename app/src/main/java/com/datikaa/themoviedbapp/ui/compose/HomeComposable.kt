package com.datikaa.themoviedbapp.ui.compose

import android.print.PrintAttributes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.datikaa.themoviedbapp.ImagesBaseUrl
import com.datikaa.themoviedbapp.PicSizeW500
import com.datikaa.themoviedbapp.R
import com.datikaa.themoviedbapp.api.model.UpcomingMovie
import com.datikaa.themoviedbapp.ui.home.HomeViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsWithImePadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import dev.chrisbanes.accompanist.insets.toPaddingValues


@Composable
fun HomeComposable(navController: NavController, viewModel: HomeViewModel) {
    val itemsState = viewModel.upcomingMovies.collectAsState(emptyList())

    Scaffold(
        topBar = {
            Surface(
                color = Color.White,
                elevation = 4.dp,
            ) {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent,
                    modifier = Modifier.statusBarsPadding(),
                    title = { Text(text = "Title", Modifier.padding(LocalWindowInsets.current.navigationBars.toPaddingValues(
                        top = false,
                        start = true,
                        end = false,
                        bottom = false,
                    ))) })
            }
        }) {

        LazyColumn(
            contentPadding = LocalWindowInsets.current.navigationBars.toPaddingValues(
                top = false,
                additionalStart = 8.dp,
                additionalEnd = 8.dp,
                additionalTop = 8.dp,
                additionalBottom = 8.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(itemsState.value) {
                HomeItemComposable(upcomingMovie = it)
            }
        }
    }
}

@Composable
internal inline fun <reified T : ViewModel> NavBackStackEntry.hiltViewModel(): T {
    return ViewModelProvider(
        this.viewModelStore,
        HiltViewModelFactory(LocalContext.current, this)
    ).get(T::class.java)
}

@Composable
fun HomeItemComposable(upcomingMovie: UpcomingMovie) {
    Card(modifier = Modifier.aspectRatio(1.77f)) {
        CoilImage(
            data = ImagesBaseUrl + PicSizeW500 + upcomingMovie.backdrop_path,
            fadeIn = true,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            loading = {
                val image: Painter = painterResource(id = R.drawable.pic_loading_placeholder)
                Image(painter = image, contentDescription = "")
            },
            modifier = Modifier.fillMaxWidth()
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