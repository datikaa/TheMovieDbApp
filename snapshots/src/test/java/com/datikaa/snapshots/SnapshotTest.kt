package com.datikaa.snapshots

import app.cash.paparazzi.Paparazzi
import com.datikaa.themoviedbapp.ui.compose.ComposableForSnapshot
import org.junit.Rule
import org.junit.Test

class SnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun launchComposable() {
        paparazzi.snapshot {
            ComposableForSnapshot()
        }
    }
}