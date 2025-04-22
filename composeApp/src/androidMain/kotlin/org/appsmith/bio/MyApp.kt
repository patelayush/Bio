package org.appsmith.bio

import android.app.Application
import shared.AppContext

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContext.setUp(applicationContext)
    }
}