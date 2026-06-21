package org.example.project

import android.app.Application
import org.example.project.db.appContext

class PerksApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}