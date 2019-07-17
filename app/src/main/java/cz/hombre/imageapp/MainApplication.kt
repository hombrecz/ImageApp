package cz.hombre.imageapp

import android.app.Application
import cz.hombre.imageapp.module.appModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}