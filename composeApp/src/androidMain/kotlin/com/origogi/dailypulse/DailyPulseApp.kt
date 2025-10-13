package com.origogi.dailypulse

import android.app.Application
import com.origogi.dailypulse.di.databaseModule
import com.origogi.dailypulse.di.sharedKoinModules
import com.origogi.dailypulse.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DailyPulseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}