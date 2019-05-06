package com.andieheung.mastermind_kotlin_mvvm.manager

import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSetting
import com.andieheung.mastermind_kotlin_mvvm.model.*
import java.util.HashMap
import java.util.concurrent.ThreadLocalRandom

class GameDataManagerImpl():GameDataManager{

    lateinit var gameSetting: GameSetting
    private var gameStatus: GameStatus? = null

    override var currentGuess: PinColorList? = null
    override var gameAns: PinColorList? = null

    override val numAttempt: Int
        get() = gameStatus!!.numAttempt

    override val timeSpent: Long
        get() = gameStatus!!.timeSpent

    companion object {

        @Volatile private var INSTANCE: GameDataManagerImpl? = null

        fun getInstance(): GameDataManagerImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameDataManagerImpl().also { INSTANCE = it }
            }
    }

    override fun init(gSetting: GameSetting) {

        if (INSTANCE != null) {
            this.gameStatus = GameStatus.getInstance()

            gameSetting = gSetting
            gameStatus!!.gStarted = false
            gameStatus!!.gPause = false
            gameStatus!!.numAttempt = 0
            gameStatus!!.timeSpent = 0
        }
    }

    override fun onStartGame() {

        currentGuess = getEmptyPinArr(gameSetting.numPin)
        gameAns = generateAns()

        gameStatus!!.gStarted = true
        gameStatus!!.gPause = false
        gameStatus!!.numAttempt = 0
        gameStatus!!.timeSpent = 0
    }

    override fun onEndGame() {

        currentGuess = null
        gameAns = null

        gameStatus!!.gStarted = false
        gameStatus!!.gPause = false
        gameStatus!!.numAttempt = 0
        gameStatus!!.timeSpent = 0

    }

    override fun onResumeGame() {
        gameStatus!!.gPause = false
    }

    override fun onPauseGame() {
        gameStatus!!.gPause = true
    }

    override fun gameStarted(): Boolean {
        return gameStatus!!.gStarted
    }

    override fun gameStartedPaused(): Boolean {
        return gameStatus!!.gStarted && gameStatus!!.gPause
    }

    override fun gameStartedNotPaused(): Boolean {
        return gameStatus!!.gStarted && !gameStatus!!.gPause
    }

    override fun remainingAttempt(): Int {
        return gameSetting.numAttempt - gameStatus!!.numAttempt
    }

    override fun incrementNumAttempt() {
        gameStatus!!.incrementNumAttempt()
    }

    override fun incrementTimeSpent() {
        gameStatus!!.incrementTimeSpent()
    }

    override fun completeGuessAttempt(): Boolean {

        return currentGuess!!.isComplete
    }

    override fun updateCurrentGuess(pos: Int, pinColorOrdinal: Int) {
        currentGuess!!.setPin(pos, pinColorOrdinal)
    }

    override fun checkIfBingo(): Boolean {
        return currentGuess!!.allColorMatches(gameAns!!)
    }

    override fun reachMaxAttempt(): Boolean {
        return gameStatus!!.numAttempt === gameSetting.numAttempt
    }

    private fun getEmptyPinArr(numPin: Int): PinColorList {
        return PinColorListImpl(numPin)
    }

    override fun resetGuessAttempt() {
        currentGuess = getEmptyPinArr(gameSetting.numPin)
    }

    override fun generateAns(): PinColorList {
        val min = 1
        val max = gameSetting.numColor + 1
        val ans = PinColorListImpl(gameSetting.numPin)

        for (i in 0 until gameSetting.numPin) {
            val rNum = ThreadLocalRandom.current().nextInt(min, max)
            ans.setPin(i, rNum)
        }
        return ans
    }

    override fun generateHint(): Hint {

        var numMatch = 0
        var numColorOnly = 0

        val guessCopy = currentGuess!!.copyList
        val ansCopy = gameAns!!.copyList

        for (i in gameSetting.numPin - 1 downTo 0) {
            if (guessCopy.get(i).equals(ansCopy.get(i))) {
                guessCopy.removeAt(i)
                ansCopy.removeAt(i)
                numMatch++
            }
        }

        val ansCnts = getColorCnts(ansCopy)
        val guessCnts = getColorCnts(guessCopy)

        for ((key, value) in ansCnts) {
            if (guessCnts.containsKey(key)) {
                if (guessCnts[key]!! >= value) {
                    numColorOnly += value
                } else if (guessCnts[key]!! < value) {
                    numColorOnly += guessCnts[key]!!
                }
            }
        }

        val numNotMatch = gameSetting.numPin - numMatch - numColorOnly

        val hint = HintImpl()

        for (i in 0 until numMatch)
            hint.add(HintSymbol.Match)
        for (i in 0 until numColorOnly)
            hint.add(HintSymbol.ColorOnly)
        for (i in 0 until numNotMatch)
            hint.add(HintSymbol.NotMatch)

        return hint
    }

    private fun getColorCnts(list: List<PinColor>): Map<PinColor, Int> {
        val cnts = HashMap<PinColor, Int>()

        for (item in list) {
            if (cnts.containsKey(item))
                cnts[item] = cnts[item]!! + 1
            else
                cnts[item] = 1
        }
        return cnts
    }
}
