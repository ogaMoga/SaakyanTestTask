package com.ogamoga.developerslive

import android.app.Application
import com.ogamoga.developerslive.di.databaseModule
import com.ogamoga.developerslive.di.networkModule
import com.ogamoga.developerslive.di.repositoryModule
import com.ogamoga.developerslive.di.screenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    companion object {
        const val SECTION_TYPE_KEY = "section_id"
        const val CURRENT_ID_KEY = "current_item_id"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                screenModule
            )
        }
    }
}