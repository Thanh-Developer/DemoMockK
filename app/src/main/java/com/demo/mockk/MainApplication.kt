package com.demo.mockk

import android.app.Application
import com.demo.mockk.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Create by ThanhPQ
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    retrofitModule,
                    apiModule,
                    validateModule
                )
            )
        }
    }
}
