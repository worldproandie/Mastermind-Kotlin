package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

class GameAttemptsRepository (private val gameAttemptLocal: GameAttemptsLocalDataSource) :
    GameAttemptsDataSource {

    var cachedGameAttempts: LinkedHashMap<Long, GameAttempt> = LinkedHashMap()
    var cacheIsDirty = false

    override fun getAll(callback: GameAttemptsDataSource.LoadEntriesCallback){
        if (cachedGameAttempts.isNotEmpty() && !cacheIsDirty) {
            callback.onEntriesLoaded(ArrayList(cachedGameAttempts.values))
        }else{

            gameAttemptLocal.getAll(object : GameAttemptsDataSource.LoadEntriesCallback {
                override fun onEntriesLoaded(gameAttempts: List<GameAttempt>) {
                    refreshCache(gameAttempts)
                    callback.onEntriesLoaded(ArrayList(cachedGameAttempts.values))
                }

                override fun onDataNotAvailable() {
                }
            })
        }
    }

    override fun getEntry(id: String, callback: GameAttemptsDataSource.GetEntryCallback){}

    override fun saveEntry(gameAttempt: GameAttempt){
        cacheAndPerform(gameAttempt){
            gameAttemptLocal.saveEntry(it)
        }
    }

    override fun deleteEntry(id : String){}

    override fun deleteAll(){}

    private fun refreshCache(gameAttempts: List<GameAttempt>) {
        cachedGameAttempts.clear()
        gameAttempts.forEach {
            cacheAndPerform(it) {}
        }
        cacheIsDirty = false
    }

    private inline fun cacheAndPerform(gameAttempt: GameAttempt, perform: (GameAttempt) -> Unit) {
        cachedGameAttempts[gameAttempt.id] = gameAttempt
        perform(gameAttempt)
    }

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

