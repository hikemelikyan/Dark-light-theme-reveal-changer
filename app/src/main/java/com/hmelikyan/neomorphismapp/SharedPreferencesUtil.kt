package com.hmelikyan.neomorphismapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


class SharedPreferencesUtil(context: Context) {
    private val mShared: SharedPreferences =
        context.getSharedPreferences("Configs", Context.MODE_PRIVATE)

    fun setStringSharedPreferences(key: String, value: String) {
        mShared.edit(commit = true) { putString(key, value) }
    }

    fun getStringSharedPreferences(key: String): String? {
        return mShared.getString(key, null)
    }

    fun getStringSharedPreferences(key: String, defValue: String): String? {
        return mShared.getString(key, defValue)
    }

    fun setIntSharedPreferences(key: String, value: Int) {
        mShared.edit(true) { putInt(key, value) }
    }

    fun getIntSharedPreferences(key: String): Int {
        return mShared.getInt(key, 0)
    }

    fun setBooleanSharedPreferences(key: String, value: Boolean) {
        mShared.edit(true) { putBoolean(key, value) }
    }

    fun getBooleanSharedPreferences(key: String): Boolean? {
        return mShared.getBoolean(key, false)
    }


    fun getSharedPreferences(): SharedPreferences {
        return mShared
    }
}