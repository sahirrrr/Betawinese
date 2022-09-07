package com.skripsi.betawinese

import android.app.Application
import com.skripsi.betawinese.core.di.betawineseModule
import com.skripsi.betawinese.core.di.databaseModule
import com.skripsi.betawinese.core.di.networkModule
import com.skripsi.betawinese.di.useCaseModule
import com.skripsi.betawinese.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    betawineseModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}