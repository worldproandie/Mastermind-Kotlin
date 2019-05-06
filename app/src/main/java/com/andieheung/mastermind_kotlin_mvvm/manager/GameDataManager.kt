package com.andieheung.mastermind_kotlin_mvvm.manager

import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSetting
import com.andieheung.mastermind_kotlin_mvvm.model.Hint
import com.andieheung.mastermind_kotlin_mvvm.model.PinColorList

interface GameDataManager {

    var currentGuess: PinColorList?
    var gameAns: PinColorList?
    val numAttempt: Int
    val timeSpent: Long

    fun init(gameSetting: GameSetting)
    fun onStartGame()
    fun onEndGame()
    fun onResumeGame()
    fun onPauseGame()

    fun gameStarted(): Boolean
    fun gameStartedPaused(): Boolean
    fun gameStartedNotPaused(): Boolean
    fun remainingAttempt(): Int
    fun incrementNumAttempt()
    fun incrementTimeSpent()
    fun completeGuessAttempt(): Boolean
    fun updateCurrentGuess(pos: Int, pinColorOrdinal: Int)
    fun checkIfBingo(): Boolean
    fun reachMaxAttempt(): Boolean
    fun resetGuessAttempt()
    fun generateHint(): Hint
    fun generateAns(): PinColorList

}