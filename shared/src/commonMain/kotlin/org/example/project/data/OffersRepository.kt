package org.example.project.data

import kotlinx.coroutines.flow.Flow
import org.example.project.Offer
import org.example.project.data.local.OffersDao
import org.example.project.data.remote.OffersApi

class OffersRepository(
    private val api: OffersApi,
    private val dao: OffersDao,
) {
    fun observeOffers(): Flow<List<Offer>> = dao.observeAll()

    suspend fun refresh() {
        val fresh = api.getOffers()
        dao.upsertAll(fresh)
    }
}