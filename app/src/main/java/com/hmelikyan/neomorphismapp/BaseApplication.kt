package com.hmelikyan.neomorphismapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class BaseApplication : Application() {

    override fun onCreate() {
        val shared = getSharedPreferences()
        if (shared.getBooleanSharedPreferences("isDarkEnabled") != null && shared.getBooleanSharedPreferences("isDarkEnabled")!!) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate()
    }
}