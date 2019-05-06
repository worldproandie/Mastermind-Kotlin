package com.andieheung.mastermind_kotlin_mvvm.data.game_setting

import com.andieheung.mastermind_kotlin_mvvm.data.GameSharedPreferences

class GameSettingLocalDataSource (private val gameSharedPreferences : GameSharedPreferences): GameSettingDataSource{

    override fun getGameSetting():GameSetting{
        val playerName = gameSharedPreferences.readPref("playerName", "MMPlayer") as String
        val numPin = gameSharedPreferences.readPref("numPin", 4) as Int
        val numColor = gameSharedPreferences.readPref("numColor", 5) as Int
        val numAttempt = gameSharedPreferences.readPref("numAttempt", 12) as Int

        return GameSetting(playerName, numPin, numColor, numAttempt)
    }

    override fun saveGameSetting(gameSetting: GameSetting){
        gameSharedPreferences.writePref("playerName", gameSetting.playerName)
        gameSharedPreferences.writePref("numPin", gameSetting.numPin)
        gameSharedPreferences.writePref("numColor", gameSetting.numColor)
        gameSharedPreferences.writePref("numAttempt", gameSetting.numAttempt)
    }

    companion object {
        private var INSTANCE: GameSettingLocalDataSource? = null

        @JvmStatic
        fun getInstance(gameSharedPreferences: GameSharedPreferences): GameSettingLocalDataSource {
            if (INSTANCE == null) {
                synchronized(GameSettingLocalDataSource::javaClass) {
                    INSTANCE = GameSettingLocalDataSource(gameSharedPreferences)
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}