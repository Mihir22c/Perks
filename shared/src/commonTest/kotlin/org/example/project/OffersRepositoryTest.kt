package org.example.project

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.example.project.data.OffersRepository
import org.example.project.data.local.OffersDao
import org.example.project.data.remote.OffersApi
import kotlin.test.Test
import kotlin.test.assertEquals

private class FakeOffersApi(private val toReturn: List<Offer>) : OffersApi {
    override suspend fun getOffers(): List<Offer> = toReturn
}

private class FakeOffersDao : OffersDao {
    private val stored = MutableStateFlow<List<Offer>>(emptyList())
    override fun observeAll(): Flow<List<Offer>> = stored
    override fun upsertAll(offers: List<Offer>) { stored.update { offers } }
}

class OffersRepositoryTest {

    @Test
    fun refresh_fetches_from_api_and_exposes_via_flow() = runTest {
        val api = FakeOffersApi(listOf(Offer(1, "Lipstick", "desc", "img")))
        val dao = FakeOffersDao()
        val repo = OffersRepository(api, dao)

        // offline-first: starts empty (nothing in DB)
        assertEquals(emptyList(), repo.observeOffers().first())

        // refresh writes API result into DAO; flow emits it
        repo.refresh()
        val offers = repo.observeOffers().first()

        assertEquals(1, offers.size)
        assertEquals("Lipstick", offers.first().title)
    }
}