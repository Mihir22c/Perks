package org.example.project.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.Offer
import org.example.project.db.PerksDatabase

class OffersDao(db: PerksDatabase) {
    private val queries = db.offerQueries

    fun observeAll(): Flow<List<Offer>> =
        queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { rows ->
                rows.map { row ->
                    Offer(
                        id = row.id.toInt(),
                        title = row.title,
                        description = row.description,
                        imageUrl = row.imageUrl,
                    )
                }
            }

    fun upsertAll(offers: List<Offer>) {
        queries.transaction {
            offers.forEach { offer ->
                queries.upsert(
                    id = offer.id.toLong(),
                    title = offer.title,
                    description = offer.description,
                    imageUrl = offer.imageUrl,
                    isFavourite = 0L,
                )
            }
        }
    }
}