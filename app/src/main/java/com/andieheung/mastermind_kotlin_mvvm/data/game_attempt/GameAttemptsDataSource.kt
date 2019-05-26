package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

interface GameAttemptsDataSource {

    interface LoadEntriesCallback {

        fun onEntriesLoaded(gameAttempts: List<GameAttempt>)

        fun onDataNotAvailable()
    }

    interface GetEntryCallback {

        fun onEntryLoaded(gameAttempt: GameAttempt)

        fun onDataNotAvailable()
    }

    fun getAll(callback: LoadEntriesCallback)

    fun getEntry(id: String, callback: GetEntryCallback)

    fun saveEntry(gameAttempt: GameAttempt)

    fun deleteEntry(id : String)

    fun deleteAll()
}