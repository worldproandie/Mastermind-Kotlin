package com.andieheung.mastermind_kotlin_mvvm.utils

import android.content.Context
import com.andieheung.mastermind_kotlin_mvvm.data.GameDatabase
import com.andieheung.mastermind_kotlin_mvvm.data.GameSharedPreferences
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttemptsLocalDataSource
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttemptsRepository
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSettingLocalDataSource
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSettingRepository

object Injection {

    fun provideGameAttemptsRepository(context: Context): GameAttemptsRepository {
        val database = GameDatabase.getInstance(context)
        return GameAttemptsRepository.getInstance(
            GameAttemptsLocalDataSource.getInstance(AppExecutors(), database.gameAttemptDao()))
    }

    fun provideGameSettingRepository(context: Context): GameSettingRepository {
        val gameSharedPreferences = GameSharedPreferences.getInstance(context)
        return GameSettingRepository.getInstance(
            GameSettingLocalDataSource.getInstance(gameSharedPreferences))
    }
}
