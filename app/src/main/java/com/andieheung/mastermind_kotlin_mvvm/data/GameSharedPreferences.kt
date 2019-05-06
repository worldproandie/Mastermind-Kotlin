package com.andieheung.mastermind_kotlin_mvvm.data

import android.content.Context
import android.content.SharedPreferences

class GameSharedPreferences constructor(val context: Context) {

    private val SHARED_PREFERENCES_NAME = "mastermind"

    private var prefs: SharedPreferences = this.context.getSharedPreferences(
        SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE
    )
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun writePref(key: String, value : Any){
        when(value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            else -> error("Invalid value type!")
        }
        editor.apply()
    }

    fun readPref(key: String, default: Any) : Any{
        return when(default) {
            is String -> prefs.getString(key, default)
            is Int -> prefs.getInt(key, default)
            is Boolean -> prefs.getBoolean(key, default)
            is Long -> prefs.getLong(key, default)
            is Float -> prefs.getFloat(key, default)
            else -> error("Invalid key!")
        }
    }

    companion object {
        @Volatile private var INSTANCE: GameSharedPreferences? = null

        fun getInstance(context: Context): GameSharedPreferences =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameSharedPreferences(context).also { INSTANCE = it }
            }
    }
}