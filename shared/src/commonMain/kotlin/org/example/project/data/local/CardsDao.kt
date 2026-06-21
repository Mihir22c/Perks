package org.example.project.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.Card
import org.example.project.db.PerksDatabase

class CardsDao(db: PerksDatabase) {
    private val queries = db.cardQueries

    fun observeAll(): Flow<List<Card>> =
        queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { rows -> rows.map { Card(it.id, it.name) } }

    fun insert(card: Card) = queries.insert(card.id, card.name)

    fun delete(id: Long) = queries.deleteById(id)
}