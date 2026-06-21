package org.example.project.ui.offers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.Offer

@Composable
fun OffersScreen(viewModel: OffersViewModel) {
    val state by viewModel.state.collectAsState()

    Box(Modifier.fillMaxSize()) {
        when {
            state.offers.isEmpty() && state.isLoading ->
                CircularProgressIndicator(Modifier.align(Alignment.Center))

            state.offers.isEmpty() && state.error != null ->
                Text(
                    "Error: ${state.error}",
                    Modifier.align(Alignment.Center).padding(24.dp)
                )

            else -> LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.offers) { offer -> OfferCard(offer) }
            }
        }
    }
}

@Composable
private fun OfferCard(offer: Offer) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = offer.imageUrl,
                contentDescription = offer.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(72.dp),
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(offer.title, style = MaterialTheme.typography.titleMedium, maxLines = 1)
                Spacer(Modifier.height(4.dp))
                Text(
                    offer.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                )
            }
        }
    }
}