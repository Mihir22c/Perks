package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import org.example.project.ui.offers.OffersScreen
import org.example.project.ui.offers.OffersViewModel

@Composable
fun App() {
    MaterialTheme {
        Surface {
            val viewModel = remember { OffersViewModel(createOffersRepository()) }
            OffersScreen(viewModel)
        }
    }
}