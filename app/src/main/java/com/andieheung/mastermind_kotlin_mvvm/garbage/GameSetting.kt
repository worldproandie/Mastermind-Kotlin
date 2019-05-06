package com.andieheung.mastermind_kotlin_mvvm.garbage

//package com.andieheung.mastermind_kotlin_mvvm.model
//
//import android.content.Context
//
//class GameSetting private constructor(context: Context) : GameSharedPreferences(context) {
//
//    var playerName: String
//        get() = readPref("playerName", "MMPlayer") as String
//        set(name) {
//            writePref("playerName", name)
//        }
//
//    var numPin: Int
//        get() = readPref("numPin", 4) as Int
//        set(num) {
//            writePref("numPin", num)
//        }
//
//    var numColor: Int
//        get() = readPref("numColor", 5) as Int
//        set(num) {
//            writePref("numColor", num)
//        }
//
//    var numAttempt: Int
//        get() = readPref("numAttempt", 12) as Int
//        set(num) {
//            writePref("numAttempt", num)
//        }
//
//    companion object {
//        @Volatile private var INSTANCE: GameSetting? = null
//
//        fun getInstance(context: Context): GameSetting =
//            INSTANCE ?: synchronized(this) {
//            INSTANCE ?: GameSetting(context).also { INSTANCE = it }
//        }
//    }
//}