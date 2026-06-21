package org.example.project.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual fun createDatabaseDriver(): SqlDriver =
    NativeSqliteDriver(PerksDatabase.Schema, "perks.db")