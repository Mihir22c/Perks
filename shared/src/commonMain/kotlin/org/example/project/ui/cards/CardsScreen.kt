package org.example.project.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.Card as LoyaltyCard

@Composable
fun CardsScreen(viewModel: CardsViewModel) {
    val state by viewModel.state.collectAsState()
    var showAdd by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf<LoyaltyCard?>(null) }

    val current = selected
    if (current != null) {
        CardDetailScreen(
            card = current,
            number = viewModel.cardNumber(current.id),
            onBack = { selected = null },
        )
        return
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAdd = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add card")
            }
        }
    ) { padding ->
        if (state.cards.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No cards yet. Tap + to add one.")
            }
        } else {
            LazyColumn(
                Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.cards) { card ->
                    ElevatedCard(Modifier.fillMaxWidth().clickable { selected = card }) {
                        Text(
                            card.name,
                            Modifier.padding(20.dp),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }
    }

    if (showAdd) {
        AddCardDialog(
            onDismiss = { showAdd = false },
            onAdd = { name, number ->
                viewModel.addCard(name, number)
                showAdd = false
            },
        )
    }
}

@Composable
private fun AddCardDialog(onDismiss: () -> Unit, onAdd: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add loyalty card") },
        text = {
            Column {
                OutlinedTextField(name, { name = it }, label = { Text("Store name") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(number, { number = it }, label = { Text("Card number") }, singleLine = true)
            }
        },
        confirmButton = { TextButton({ onAdd(name, number) }) { Text("Add") } },
        dismissButton = { TextButton(onDismiss) { Text("Cancel") } },
    )
}