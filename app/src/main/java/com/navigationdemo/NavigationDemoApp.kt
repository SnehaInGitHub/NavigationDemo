package com.navigationdemo

import android.app.Application

class NavigationDemoApp : Application() {
    companion object {
        lateinit var navigationDemoApp: NavigationDemoApp
    }

    override fun onCreate() {
        super.onCreate()
        navigationDemoApp = this
    }
}