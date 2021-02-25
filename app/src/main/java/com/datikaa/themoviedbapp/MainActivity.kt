package com.datikaa.themoviedbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.datikaa.themoviedbapp.ui.compose.HomeComposable
import com.datikaa.themoviedbapp.ui.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ExperimentalAnimatedInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @ExperimentalAnimatedInsets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HiltViewModelFactory(LocalContext.current, it)
                        HomeComposable(navController, it.hiltViewModel())
                    }
                }
            }
        }
    }
}
