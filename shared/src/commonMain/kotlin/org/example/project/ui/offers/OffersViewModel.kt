package org.example.project.ui.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.example.project.data.OffersRepository

class OffersViewModel(
    private val repository: OffersRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(OffersUiState())
    val state: StateFlow<OffersUiState> = _state.asStateFlow()

    init {
        // 1. observe DB — single source of truth, updates UI on any DB change
        repository.observeOffers()
            .onEach { offers -> _state.value = _state.value.copy(offers = offers) }
            .launchIn(viewModelScope)

        // 2. kick a refresh on start
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                repository.refresh()
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message ?: "Something went wrong")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}