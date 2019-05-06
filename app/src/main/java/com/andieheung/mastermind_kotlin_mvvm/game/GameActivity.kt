package com.andieheung.mastermind_kotlin_mvvm.game

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.andieheung.mastermind_kotlin_mvvm.deprecated.GameMVPContract
import com.andieheung.mastermind_kotlin_mvvm.deprecated.GamePresenter
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.help.HelpActivity
import com.andieheung.mastermind_kotlin_mvvm.menu.MenuActivity
import com.andieheung.mastermind_kotlin_mvvm.model.GameResultFlag
import com.andieheung.mastermind_kotlin_mvvm.model.Hint
import com.andieheung.mastermind_kotlin_mvvm.model.PinColorList
import com.andieheung.mastermind_kotlin_mvvm.utils.Injection
import com.andieheung.mastermind_kotlin_mvvm.utils.MMConstants
import com.andieheung.mastermind_kotlin_mvvm.utils.createBtn
import com.andieheung.mastermind_kotlin_mvvm.view_components.action_bar.GameActionBar
import com.andieheung.mastermind_kotlin_mvvm.view_components.adapter.AttemptListAdapter
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment4Pins
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment5Pins
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment6Pins
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogHandler
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.MMAnsDialogFragment
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.MMDialogFragment
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogCancellableHandler
import java.util.ArrayList

class GameActivity : AppCompatActivity(), GameMVPContract.GameView {

    private lateinit var listView: ListView
    private var colorPickerFragment: ColorPickerFragment? = null
    private lateinit var btnGuess: Button
    private lateinit var btnPlayPause: Button
    private lateinit var timerTxt: TextView
    private lateinit var attemptTxt: TextView
    private lateinit var actionBar: GameActionBar
    private lateinit var gamePresenter: GameMVPContract.GamePresenter

    private val fragmentTransaction: androidx.fragment.app.FragmentTransaction
        get() {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            val prevFragments = fm.fragments
            if (prevFragments != null && !prevFragments.isEmpty()) {
                for (fragment in prevFragments) {
                    ft.remove(fragment)
                }
            }
            ft.addToBackStack(null)
            return ft
        }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_game)

        val gSettingRepository = Injection.provideGameSettingRepository(applicationContext)
        val gAttemptsRepository = Injection.provideGameAttemptsRepository(applicationContext)

        gamePresenter = GamePresenter.getInstance(gSettingRepository, gAttemptsRepository)
        gamePresenter.initGameView(this)
    }

    override fun initGameUI(gameNumPin: Int,
                            guessList: ArrayList<PinColorList>, hintList: ArrayList<Hint>) {
        listView = findViewById<ListView>(R.id.pAttemptDisplay)

        btnGuess = createBtn(GameButtonClick(), R.id.btn_guess)
        btnPlayPause = createBtn(GameButtonClick(), R.id.btn_play_pause)
        btnGuess.isEnabled = false
        btnPlayPause.text = "Play"

        timerTxt = findViewById<TextView>(R.id.gTimer)
        attemptTxt = findViewById<TextView>(R.id.gAttemptRemain)
        attemptTxt.text = ""

        actionBar = GameActionBar(this, GameActionBarButtonClick())

        val ft = supportFragmentManager.beginTransaction()
        if (colorPickerFragment == null) {
            colorPickerFragment = initColorPickerFragmentInstance(gameNumPin)
            ft.add(R.id.guess_panel, colorPickerFragment!!).commit()
        }

        val gAdapter = AttemptListAdapter(this@GameActivity)

        gAdapter.setGameData(gameNumPin, guessList, hintList)
        listView.adapter = gAdapter

        gAdapter.notifyDataSetChanged()
    }

    public override fun onResume() {
        super.onResume()
        gamePresenter.resumeGameActivity(this)
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onDestroy() {
        super.onDestroy()
        gamePresenter.destroyGameActivity()
    }

    private fun initColorPickerFragmentInstance(num: Int): ColorPickerFragment {
        val instance: ColorPickerFragment
        when (num) {

            6 -> instance = ColorPickerFragment6Pins()
            5 -> instance = ColorPickerFragment5Pins()
            4 -> instance = ColorPickerFragment4Pins()
            else -> instance = ColorPickerFragment4Pins()
        }

        instance.gPresenter = gamePresenter

        return instance
    }

    override fun updateTimerTxt(timeStr: String) {
        timerTxt.text = timeStr
    }

    override fun updateListViewSelection() {
        listView.post { listView.setSelection(listView.count - 1) }
    }

    override fun onIncompleteAttempt() {
        Toast.makeText(applicationContext, "Your guess is not complete!", Toast.LENGTH_SHORT).show()
    }

    override fun onCompleteAttempt(remainingAttempt: Int) {
        colorPickerFragment!!.resetColorPickerBtn()
        attemptTxt.text = "Remaining Attempt(s): $remainingAttempt"
    }

    override fun onStartGame() {
        colorPickerFragment!!.colorPickerEnabled(true)
        btnGuess.isEnabled = true
        btnPlayPause.text = "Pause"
    }

    override fun onPauseGame() {
        colorPickerFragment!!.colorPickerEnabled(false)
        btnGuess.isEnabled = false
        btnPlayPause.text = "Resume"
    }

    override fun onResumeGame() {
        colorPickerFragment!!.colorPickerEnabled(true)
        btnGuess.isEnabled = true
        btnPlayPause.text = "Pause"
    }

    override fun onEndGame() {
        colorPickerFragment!!.colorPickerEnabled(false)
        btnPlayPause.text = "Play"
        timerTxt.text = "00:00"
    }

    override fun goToActivity(activity: Class<out Activity>) {

        when(activity){
            MenuActivity::class.java -> {}
            HelpActivity::class.java -> {}
            else -> error("Invalid activity!")
        }
        startActivity(Intent(applicationContext, activity))
    }

    override fun showGameWinDialog(timeSpent: String, numAttempt: Int,
                                   ans: PinColorList, dialogHandler: MMDialogHandler) {

        val title = getString(R.string.bingo)
        val msgTimeSpent = getString(R.string.time_spent) + timeSpent
        val msgNumAttempt = getString(R.string.num_attempt) + numAttempt
        val msg = String.format("%s \n %s", msgTimeSpent, msgNumAttempt)

        showDialogWithAns(ans, title, msg, dialogHandler, "gameWinDialog")
    }

    override fun showGameLoseDialog(ans: PinColorList, dialogHandler: MMDialogHandler) {


        val title = getString(R.string.lose)
        val msg = getString(R.string.ok_to_reset)

        showDialogWithAns(ans, title, msg, dialogHandler, "gameLoseDialog")
    }

    private fun showDialogWithAns(ans: PinColorList,
                                  title: String, msg: String,
                                  dialogHandler: MMDialogHandler, tag: String) {
        val dialog = MMAnsDialogFragment.initialize(ans, title, msg, dialogHandler)
        val ft = fragmentTransaction
        dialog.isCancelable = false
        dialog.show(ft, tag)
    }

    override fun showQuitGameDialog(dialogHandler: MMDialogCancellableHandler) {
        val dialog = MMDialogFragment.initialize("Are you sure to quit the game?",
                                                "Click OK to quit.", dialogHandler)
        dialog.isCancelable =false
        dialog.show(fragmentTransaction, "quitGameDialog")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {

            val handler = object : MMDialogCancellableHandler{
                override fun clickOk() {
                    gamePresenter.saveGameAttempt(GameResultFlag.GivenUp)
                    gamePresenter.endGame()
                    goToActivity(MMConstants.MENU_ACTIVITY)
                }

                override fun clickCancel() {
                    gamePresenter.resumeGame()
                }
            }.also { gamePresenter.keyBackCtrl(it) }

            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    internal inner class GameActionBarButtonClick : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.action_bar_home_game -> {

                    val handler = object : MMDialogCancellableHandler{
                            override fun clickOk() {
                                gamePresenter.saveGameAttempt(GameResultFlag.GivenUp)
                                gamePresenter.endGame()
                                goToActivity(MMConstants.MENU_ACTIVITY)
                            }

                            override fun clickCancel() {
                                gamePresenter.resumeGame()
                            }
                        }.also{gamePresenter.actionHomeCtrl(it)}

                    }
                R.id.action_bar_help_game -> gamePresenter.actionHelpCtrl()
                else -> {
                }
            }
        }
    }

    internal inner class GameButtonClick : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.btn_guess -> {
                    val handler = object : MMDialogHandler {
                        override fun clickOk() {
                            goToActivity(MMConstants.MENU_ACTIVITY)
                        }
                    }.also{gamePresenter.guessBtnCtrl(it)}
                }

                R.id.btn_play_pause -> gamePresenter.playPauseBtnCtrl()
            }
        }
    }
}