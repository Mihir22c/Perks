package org.example.project

import org.example.project.data.OffersRepository
import org.example.project.data.local.OffersDao
import org.example.project.data.remote.OffersApi
import org.example.project.db.createDatabase
import org.example.project.data.CardsRepository
import org.example.project.data.local.CardsDao

fun createOffersRepository(): OffersRepository =
    OffersRepository(
        api = OffersApi(createHttpClient()),
        dao = OffersDao(createDatabase()),
    )
fun createCardsRepository(): CardsRepository =
    CardsRepository(
        dao = CardsDao(createDatabase()),
        secureStorage = createKVault(),
    )