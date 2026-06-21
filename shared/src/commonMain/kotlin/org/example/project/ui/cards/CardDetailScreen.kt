package org.example.project.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import org.example.project.Card as LoyaltyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailScreen(card: LoyaltyCard, number: String?, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(card.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (number != null) {
                Image(
                    painter = rememberQrCodePainter(number),
                    contentDescription = "Card code",
                    modifier = Modifier.size(240.dp),
                )
                Spacer(Modifier.height(16.dp))
                Text(number, style = MaterialTheme.typography.titleMedium)
            } else {
                Text("Could not load card number.")
            }
        }
    }
}