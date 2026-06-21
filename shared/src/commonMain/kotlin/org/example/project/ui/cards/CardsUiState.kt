package org.example.project.ui.cards

import org.example.project.Card

data class CardsUiState(
    val cards: List<Card> = emptyList(),
)