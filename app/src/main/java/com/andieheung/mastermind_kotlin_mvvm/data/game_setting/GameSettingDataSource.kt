package com.andieheung.mastermind_kotlin_mvvm.data.game_setting

interface GameSettingDataSource {

    fun getGameSetting():GameSetting

    fun saveGameSetting(gameSetting: GameSetting)
}