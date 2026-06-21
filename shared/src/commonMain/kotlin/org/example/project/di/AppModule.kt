package org.example.project.di

import org.example.project.createHttpClient
import org.example.project.createKVault
import org.example.project.data.CardsRepository
import org.example.project.data.OffersRepository
import org.example.project.data.local.CardsDao
import org.example.project.data.local.OffersDao
import org.example.project.data.remote.OffersApi
import org.example.project.db.createDatabase
import org.example.project.ui.cards.CardsViewModel
import org.example.project.ui.offers.OffersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // networking
    single { createHttpClient() }
    single { OffersApi(get()) }

    // database
    single { createDatabase() }
    single { OffersDao(get()) }
    single { CardsDao(get()) }

    // secure storage
    single { createKVault() }

    // repositories
    single { OffersRepository(get(), get()) }
    single { CardsRepository(get(), get()) }

    // viewmodels
    viewModelOf(::OffersViewModel)
    viewModelOf(::CardsViewModel)
}