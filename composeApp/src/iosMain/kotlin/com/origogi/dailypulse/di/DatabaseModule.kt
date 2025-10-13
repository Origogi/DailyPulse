package com.origogi.dailypulse.di

import com.origogi.dailypulse.db.DailyPulseDatabase
import com.origogi.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single {
        DatabaseDriverFactory().createDriver()
    }

    single {
        DailyPulseDatabase(get())
    }
}