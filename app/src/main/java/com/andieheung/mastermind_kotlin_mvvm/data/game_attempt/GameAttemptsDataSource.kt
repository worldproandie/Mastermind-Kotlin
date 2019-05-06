package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

interface GameAttemptsDataSource {

    interface LoadGameAttemptsCallback {

        fun onEntriesLoaded(gameAttempts: List<GameAttempt>)

        fun onDataNotAvailable()
    }

    interface GetGameAttemptCallback {

        fun onEntryLoaded(gameAttempt: GameAttempt)

        fun onDataNotAvailable()
    }

    fun getAll(callback: LoadGameAttemptsCallback)

    fun getEntry(id: String, callback: GetGameAttemptCallback)

    fun saveEntry(gameAttempt: GameAttempt)

    fun deleteEntry(id : String)

    fun deleteAll()
}