package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

class GameAttemptsRepository (private val gameAttemptLocal: GameAttemptsLocalDataSource) :
    GameAttemptsDataSource {

//    var cachedGameAttempts: LinkedHashMap<String, GameAttempt> = LinkedHashMap()
//    var cacheIsDirty = false

    override fun getAll(callback: GameAttemptsDataSource.LoadGameAttemptsCallback){
        gameAttemptLocal.getAll(callback)
    }

    override fun getEntry(id: String, callback: GameAttemptsDataSource.GetGameAttemptCallback){}

    override fun saveEntry(gameAttempt: GameAttempt){
        gameAttemptLocal.saveEntry(gameAttempt)
    }

    override fun deleteEntry(id : String){}

    override fun deleteAll(){}

    companion object {

        private var INSTANCE: GameAttemptsRepository? = null

        @JvmStatic fun getInstance(gameAttemptsLocalDataSource: GameAttemptsLocalDataSource) =
            INSTANCE ?: synchronized(GameAttemptsRepository::class.java) {
                INSTANCE ?: GameAttemptsRepository(gameAttemptsLocalDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}

