package org.example.project.db

import app.cash.sqldelight.db.SqlDriver

expect fun createDatabaseDriver(): SqlDriver

fun createDatabase(): PerksDatabase = PerksDatabase(createDatabaseDriver())