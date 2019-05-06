package com.andieheung.mastermind_kotlin_mvvm.data.game_setting

class GameSettingRepository (private val gameSettingLocalDataSource: GameSettingLocalDataSource) : GameSettingDataSource{

    override fun getGameSetting():GameSetting {
        return gameSettingLocalDataSource.getGameSetting()
    }

    override fun saveGameSetting(gameSetting: GameSetting) {
        gameSettingLocalDataSource.saveGameSetting(gameSetting)
    }

    companion object {

        private var INSTANCE: GameSettingRepository? = null

        @JvmStatic fun getInstance(gameSettingLocalDataSource: GameSettingLocalDataSource) =
            INSTANCE ?: synchronized(GameSettingRepository::class.java) {
                INSTANCE ?: GameSettingRepository(gameSettingLocalDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}