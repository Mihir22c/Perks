package org.example.project.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.Offer
import org.example.project.db.PerksDatabase

interface OffersDao {
    fun observeAll(): Flow<List<Offer>>
    fun upsertAll(offers: List<Offer>)
}

class SqlOffersDao(db: PerksDatabase) : OffersDao {
    private val queries = db.offerQueries

    override fun observeAll(): Flow<List<Offer>> =
        queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { rows -> rows.map { Offer(it.id.toInt(), it.title, it.description, it.imageUrl) } }

    override fun upsertAll(offers: List<Offer>) {
        queries.transaction {
            offers.forEach { queries.upsert(it.id.toLong(), it.title, it.description, it.imageUrl, 0) }
        }
    }
}