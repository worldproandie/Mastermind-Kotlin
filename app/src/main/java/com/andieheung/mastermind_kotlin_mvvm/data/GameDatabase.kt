package com.andieheung.mastermind_kotlin_mvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttempt
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttemptsDao
import com.andieheung.mastermind_kotlin_mvvm.utils.RoomTypeConverters

@Database(entities = arrayOf(GameAttempt::class), version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameAttemptDao(): GameAttemptsDao
    companion object {

        private var INSTANCE: GameDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): GameDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        GameDatabase::class.java, "GameAttempts.db")
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}