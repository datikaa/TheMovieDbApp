package com.datikaa.themoviedbapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.datikaa.themoviedbapp.ui.compose.HomeComposable
import com.datikaa.themoviedbapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by stateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            HomeComposable(viewModel)
        }
    }
}
