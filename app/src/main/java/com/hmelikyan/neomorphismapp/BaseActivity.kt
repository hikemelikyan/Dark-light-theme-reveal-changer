package com.hmelikyan.neomorphismapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppDarkTheme)
        } else
            setTheme(R.style.AppLightTheme)
        super.onCreate(savedInstanceState)
    }

    protected fun setLightStatusBar() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            else -> {
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }
    }
}

fun Context.getSharedPreferences(): SharedPreferencesUtil {
    return SharedPreferencesUtil(this)
}