package com.andieheung.mastermind_kotlin_mvvm.model


data class GameStatus private constructor(var gStarted: Boolean = false,
                                          var gPause: Boolean = false,
                                          var numAttempt: Int = 0,
                                          var timeSpent: Long = 0) {

    fun incrementNumAttempt() {
        numAttempt++
    }

    fun incrementTimeSpent() {
        timeSpent += 1000
    }

    companion object {

        private var INSTANCE: GameStatus? = null

        fun getInstance(): GameStatus {
            if (INSTANCE == null) {
                INSTANCE = GameStatus()
            }
            return INSTANCE!!
        }

    }

}
