package org.example.project.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.example.project.data.CardsRepository

class CardsViewModel(
    private val repository: CardsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CardsUiState())
    val state: StateFlow<CardsUiState> = _state.asStateFlow()

    init {
        repository.observeCards()
            .onEach { cards -> _state.value = _state.value.copy(cards = cards) }
            .launchIn(viewModelScope)
    }

    fun addCard(name: String, number: String) {
        if (name.isBlank() || number.isBlank()) return
        repository.addCard(name.trim(), number.trim())
    }

    fun deleteCard(id: Long) = repository.deleteCard(id)

    fun cardNumber(id: Long): String? = repository.getCardNumber(id)
}