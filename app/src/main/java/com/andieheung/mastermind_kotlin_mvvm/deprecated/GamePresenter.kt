package com.andieheung.mastermind_kotlin_mvvm.deprecated

import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttempt
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttemptsRepository
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSetting
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSettingRepository
import com.andieheung.mastermind_kotlin_mvvm.manager.GameDataManager
import com.andieheung.mastermind_kotlin_mvvm.manager.GameDataManagerImpl
import com.andieheung.mastermind_kotlin_mvvm.model.*
import com.andieheung.mastermind_kotlin_mvvm.utils.MMConstants
import com.andieheung.mastermind_kotlin_mvvm.utils.MMUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogCancellableHandler
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogHandler
import java.util.ArrayList

class GamePresenter private constructor(private val gSettingRepository : GameSettingRepository ,
                                        private val gAttemptsRepository: GameAttemptsRepository) : GameMVPContract.GamePresenter {
    private lateinit var gameSetting: GameSetting
    private var gameDataManager: GameDataManager = GameDataManagerImpl.getInstance()
    private lateinit var gameView: GameMVPContract.GameView
    private var gTimerImpl : GameTimer = GameTimerImpl.getInstance()
    private lateinit var guessList: ArrayList<PinColorList>
    private lateinit var hintList: ArrayList<Hint>
    private var gameUIInit = false

    companion object {

        @Volatile private var INSTANCE: GamePresenter? = null

        fun getInstance(gameSettingRepository: GameSettingRepository,
                        gameAttemptsRepository: GameAttemptsRepository): GamePresenter =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GamePresenter(gameSettingRepository, gameAttemptsRepository).also { INSTANCE = it }
            }
    }

    override fun initGameView(gView: GameMVPContract.GameView) {

        gameView = gView

        gameSetting = gSettingRepository.getGameSetting()
        gameDataManager.init(gameSetting)

        guessList = ArrayList<PinColorList>()
        hintList = ArrayList<Hint>()

        gameView.initGameUI(gameSetting.numPin, guessList, hintList)
        gameUIInit = true

        initTimer()
    }

    private fun startGame() {
        gameDataManager.onStartGame()
        gameView.onStartGame()
        resumeTimer()
    }

    private fun pauseGame() {
        gameDataManager.onPauseGame()
        gameView.onPauseGame()
        pauseTimer()
    }

    override fun resumeGame() {
        gameDataManager.onResumeGame()
        gameView.onResumeGame()
        resumeTimer()
    }

    override fun endGame() {
        gameDataManager.onEndGame()
        gameView.onEndGame()
        gameUIInit = false
        endTimer()
    }

    private fun gTimerTask(){
        gameView.updateTimerTxt(MMUtils.formatTime(gameDataManager.timeSpent))
        gameDataManager.incrementTimeSpent()
    }

    private fun initTimer() {
        gTimerImpl.init(::gTimerTask)
    }

    private fun pauseTimer() {
        gTimerImpl.pause()
    }

    private fun resumeTimer() {
        gTimerImpl.resume()
    }

    private fun endTimer() {
        gTimerImpl.end()
    }

    override fun resumeGameActivity(gView: GameMVPContract.GameView) {
        if (gameDataManager.gameStartedPaused()) {
            resumeGame()
        } else if (!gameDataManager.gameStarted() && gameUIInit) {
            endGame()
            initGameView(gView)
        }
    }

    override fun destroyGameActivity() {
        endGame()
    }

    override fun guessBtnCtrl(handler: MMDialogHandler) {

        if (gameDataManager.gameStartedNotPaused()) {

            if (!gameDataManager.completeGuessAttempt()) {

                gameView.onIncompleteAttempt()

            } else {

                gameDataManager.incrementNumAttempt()
                gameView.onCompleteAttempt(gameDataManager.remainingAttempt())

                when{
                    gameDataManager.checkIfBingo() -> gameWin(handler)
                    gameDataManager.reachMaxAttempt() -> gameLose(handler)
                    else -> gameContinue()
                }
            }
        }
    }

    private fun gameContinue() {
        hintList.add(gameDataManager.generateHint())
        guessList.add(gameDataManager.currentGuess!!)

        gameView.updateListViewSelection()
        gameDataManager.resetGuessAttempt()
    }

    private fun gameLose(handler: MMDialogHandler) {
        pauseGame()
        saveGameAttempt(GameResultFlag.Lose)
        gameView.showGameLoseDialog(gameDataManager.gameAns!!, handler)
    }

    private fun gameWin(handler: MMDialogHandler) {
        pauseGame()
        saveGameAttempt(GameResultFlag.Win)
        gameView.showGameWinDialog(
            MMUtils.formatTime(gameDataManager.timeSpent),
            gameDataManager.numAttempt,
            gameDataManager.gameAns!!,
            handler
        )
    }

    override fun playPauseBtnCtrl() {

        when{
            !gameDataManager.gameStarted() -> startGame()
            gameDataManager.gameStartedNotPaused() ->
                pauseGame().also{
                gameView.goToActivity(MMConstants.HELP_ACTIVITY)}
            gameDataManager.gameStartedPaused() -> resumeGame()
        }
    }

    override fun actionHomeCtrl(handler: MMDialogCancellableHandler) {
        if (!gameDataManager.gameStarted()) {
            gameView.goToActivity(MMConstants.MENU_ACTIVITY)
        } else {
            if (gameDataManager.gameStartedNotPaused())
                pauseGame()
            gameView.showQuitGameDialog(handler)
        }
    }

    override fun actionHelpCtrl() {
        if (gameDataManager.gameStartedNotPaused()) {
            pauseGame()
        }
        gameView.goToActivity(MMConstants.HELP_ACTIVITY)
    }

    override fun keyBackCtrl(handler: MMDialogCancellableHandler) {
        actionHomeCtrl(handler)
    }

    override fun getNumPin() : Int{
        return gameSetting.numPin
    }

    override fun getNumColor() : Int{
        return gameSetting.numColor
    }

    override fun updateCurrentGuess(index : Int , pinColorOrdinal: Int){
        gameDataManager.updateCurrentGuess(index, pinColorOrdinal)
    }

    override fun saveGameAttempt(resultFlag : GameResultFlag) {
        val gameAttempt = GameAttempt(
                                        gameSetting.playerName,
                                        gameDataManager.numAttempt,
                                        gameDataManager.timeSpent,
                                        resultFlag.ordinal,
                                        gameSetting.numPin,
                                        gameSetting.numColor
                                    )
        gAttemptsRepository.saveEntry(gameAttempt)
    }
}
