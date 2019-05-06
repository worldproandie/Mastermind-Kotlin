package com.andieheung.mastermind_kotlin_mvvm.deprecated

import android.app.Activity
import com.andieheung.mastermind_kotlin_mvvm.model.GameResultFlag
import com.andieheung.mastermind_kotlin_mvvm.model.Hint
import com.andieheung.mastermind_kotlin_mvvm.model.PinColorList
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogCancellableHandler
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogHandler
import java.util.ArrayList

interface GameMVPContract {

    interface GameView {
        fun initGameUI(numPin: Int, guessList: ArrayList<PinColorList>, hintList: ArrayList<Hint>)
        fun onStartGame()
        fun onPauseGame()
        fun onResumeGame()
        fun onEndGame()
        fun updateListViewSelection()
        fun updateTimerTxt(timeStr: String)
        fun goToActivity(activity : Class<out Activity>)
        fun showGameWinDialog(timeSpent: String, attempt:Int, ans:PinColorList, dialogHandler:MMDialogHandler)
        fun showGameLoseDialog(ans: PinColorList, dialogHandler: MMDialogHandler)
        fun showQuitGameDialog(dialogHandler: MMDialogCancellableHandler)
        fun onIncompleteAttempt()
        fun onCompleteAttempt(remainingAttempt:Int)
    }

    interface GamePresenter{
        fun initGameView(gView: GameMVPContract.GameView)
        fun resumeGameActivity(gView: GameMVPContract.GameView)
        fun destroyGameActivity()
        fun guessBtnCtrl(handler: MMDialogHandler)
        fun playPauseBtnCtrl()
        fun actionHomeCtrl(handler: MMDialogCancellableHandler)
        fun actionHelpCtrl()
        fun keyBackCtrl(handler: MMDialogCancellableHandler)
        fun getNumPin():Int
        fun getNumColor():Int
        fun updateCurrentGuess(index:Int, pinColorOrdinal:Int)
        fun endGame()
        fun resumeGame()
        fun saveGameAttempt(flag: GameResultFlag)
    }
}