package org.example.project

import org.example.project.data.OffersRepository
import org.example.project.data.local.OffersDao
import org.example.project.data.remote.OffersApi
import org.example.project.db.createDatabase

fun createOffersRepository(): OffersRepository =
    OffersRepository(
        api = OffersApi(createHttpClient()),
        dao = OffersDao(createDatabase()),
    )