package com.datikaa.themoviedbapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposableForSnapshot() {
    Card {
        Column(
            modifier = Modifier.padding(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Snapshot test")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button")
            }
        }
    }
}

@Preview
@Composable
private fun ComposableForSnapshotPreview() {
    ComposableForSnapshot()
}