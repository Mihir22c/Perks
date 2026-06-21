package org.example.project.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import android.content.Context

lateinit var appContext: Context   // set once from Application

actual fun createDatabaseDriver(): SqlDriver =
    AndroidSqliteDriver(PerksDatabase.Schema, appContext, "perks.db")