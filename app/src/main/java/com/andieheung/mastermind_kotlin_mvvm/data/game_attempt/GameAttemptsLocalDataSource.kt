package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

import com.andieheung.mastermind_kotlin_mvvm.utils.AppExecutors

class GameAttemptsLocalDataSource private constructor(
    val appExecutors: AppExecutors,
    val gameAttemptsDao: GameAttemptsDao
) : GameAttemptsDataSource {

    override fun getAll(callback: GameAttemptsDataSource.LoadEntriesCallback){
        appExecutors.diskIO.execute {
            val gameAttempts = gameAttemptsDao.getAll()
            appExecutors.mainThread.execute {
                if (gameAttempts.isEmpty()) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onEntriesLoaded(gameAttempts)
                }
            }
        }
    }

    override fun getEntry(id: String, callback: GameAttemptsDataSource.GetEntryCallback){
        appExecutors.diskIO.execute {
            val gameAttempt = gameAttemptsDao.findById(id)
            appExecutors.mainThread.execute {
                if (gameAttempt != null) {
                    callback.onEntryLoaded(gameAttempt)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveEntry(gameAttempt: GameAttempt){
        appExecutors.diskIO.execute { gameAttemptsDao.insertEntry(gameAttempt) }
    }

    override fun deleteEntry(taskId: String) {
        appExecutors.diskIO.execute { gameAttemptsDao.deleteEntry(taskId) }
    }

    override fun deleteAll() {
        appExecutors.diskIO.execute { gameAttemptsDao.deleteAll() }
    }

    companion object {
        private var INSTANCE: GameAttemptsLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, gameAttemptsDao: GameAttemptsDao): GameAttemptsLocalDataSource {
            if (INSTANCE == null) {
                synchronized(GameAttemptsLocalDataSource::javaClass) {
                    INSTANCE = GameAttemptsLocalDataSource(appExecutors, gameAttemptsDao)
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}