package com.origogi.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.origogi.dailypulse.db.DailyPulseDatabase
import com.origogi.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> {
        DatabaseDriverFactory(get()).createDriver()
    }

    single {
        DailyPulseDatabase(get())
    }
}