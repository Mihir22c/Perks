package org.example.project.ui.offers

import org.example.project.Offer

data class OffersUiState(
    val offers: List<Offer> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)