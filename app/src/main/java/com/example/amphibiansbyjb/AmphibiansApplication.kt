package com.example.amphibiansbyjb

import android.app.Application
import com.example.amphibiansbyjb.config.AppContainer
import com.example.amphibiansbyjb.config.DefaultAppContainer

// Base class for maintaining global application state
class AmphibiansApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
