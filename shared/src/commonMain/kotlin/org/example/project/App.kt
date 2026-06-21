package org.example.project

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.project.data.CardsRepository
import org.example.project.di.appModule
import org.example.project.ui.cards.CardsScreen
import org.example.project.ui.cards.CardsViewModel
import org.example.project.ui.offers.OffersScreen
import org.example.project.ui.offers.OffersViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel


private enum class AppTab { Offers, Cards }
@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            var tab by remember { mutableStateOf(AppTab.Offers) }
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = tab == AppTab.Offers,
                            onClick = { tab = AppTab.Offers },
                            icon = { Text("🎁") },
                            label = { Text("Offers") },
                        )
                        NavigationBarItem(
                            selected = tab == AppTab.Cards,
                            onClick = { tab = AppTab.Cards },
                            icon = { Text("💳") },
                            label = { Text("Cards") },
                        )
                    }
                }
            ) { padding ->
                Surface(Modifier.padding(padding)) {
                    when (tab) {
                        AppTab.Offers -> OffersScreen(koinViewModel())
                        AppTab.Cards -> CardsScreen(koinViewModel())
                    }
                }
            }
        }
    }
}

@Composable
fun CardsScreen(x0: Unit) {
    TODO("Not yet implemented")
}

@Composable
fun CardsViewModel(x0: CardsRepository) {
    TODO("Not yet implemented")
}