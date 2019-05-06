package com.andieheung.mastermind_kotlin_mvvm.setting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*

import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSetting
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSettingDataSource
import com.andieheung.mastermind_kotlin_mvvm.data.game_setting.GameSettingRepository
import com.andieheung.mastermind_kotlin_mvvm.menu.MenuActivity
import com.andieheung.mastermind_kotlin_mvvm.record.RecordActivity
import com.andieheung.mastermind_kotlin_mvvm.utils.Injection
import com.andieheung.mastermind_kotlin_mvvm.utils.createAdapter
import com.andieheung.mastermind_kotlin_mvvm.utils.createBtn
import com.andieheung.mastermind_kotlin_mvvm.utils.createSpinner
import com.andieheung.mastermind_kotlin_mvvm.view_components.action_bar.NonGameActionBar

class SettingActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var settingNumPin: Int = 0
    private var settingNumColor: Int = 0
    private var settingNumAttempt: Int = 0
    private lateinit var txtPlayerName: EditText
    private lateinit var radioGpNumPin: RadioGroup
    private lateinit var spinnerNumColor: Spinner
    private lateinit var spinnerNumAttempt: Spinner
    private lateinit var numColorAdapter: ArrayAdapter<CharSequence>
    private lateinit var numAttemptAdapter: ArrayAdapter<CharSequence>
    private lateinit var btnSave: Button
    private lateinit var btnReset: Button
    private lateinit var gameSettingRepository: GameSettingRepository
    private lateinit var gameSetting : GameSetting

    internal var btnHandler: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.setting_save -> saveSettings()
            R.id.setting_reset -> {
                resetSettings()
                refreshSettingActivity()
            }
            else -> {
            }
        }
    }

    private var radioGpListener: RadioGroup.OnCheckedChangeListener =
        RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.setting_num_pin6 -> settingNumPin = 6
                R.id.setting_num_pin5 -> settingNumPin = 5
                R.id.setting_num_pin4 -> settingNumPin = 4
                else -> settingNumPin = 4
            }
        }

    private var btnHandlerActionBar: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.action_bar_home -> {startActivity(Intent(applicationContext, MenuActivity::class.java))}
            R.id.action_bar_record -> { startActivity(Intent(applicationContext, RecordActivity::class.java)) }
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initSettingActivity()
        refreshSettingActivity()
    }

    private fun initSettingActivity() {

        val actionBar = NonGameActionBar(this@SettingActivity, btnHandlerActionBar)

        txtPlayerName = findViewById(R.id.setting_player) as EditText

        radioGpNumPin = findViewById(R.id.setting_num_pin) as RadioGroup
        radioGpNumPin.setOnCheckedChangeListener(radioGpListener)

        btnSave = createBtn(btnHandler, R.id.setting_save)
        btnReset = createBtn(btnHandler, R.id.setting_reset)

        numColorAdapter = createAdapter(this, R.array.num_color_array)
        numAttemptAdapter = createAdapter(this!!, R.array.num_attempt_array)

        spinnerNumColor =
                createSpinner(R.id.setting_num_color, numColorAdapter, this)
        spinnerNumAttempt =
                createSpinner( R.id.setting_num_attempt, numAttemptAdapter, this)
    }

    private fun refreshSettingActivity() {
        gameSettingRepository = Injection.provideGameSettingRepository(applicationContext)
        gameSetting = gameSettingRepository.getGameSetting()

        txtPlayerName.setText(gameSetting.playerName)

        val numPinBtnId = getNumPinBtnId(gameSetting.numPin)
        radioGpNumPin.check(numPinBtnId)

        val numColorPos = numColorAdapter.getPosition(Integer.toString(gameSetting.numColor))
        val numAttemptPos = numAttemptAdapter.getPosition(Integer.toString(gameSetting.numAttempt))

        spinnerNumColor.setSelection(numColorPos)
        spinnerNumAttempt.setSelection(numAttemptPos)
    }

    private fun getNumPinBtnId(num_pin: Int): Int {
        val numPinBtnId: Int
        when (num_pin) {
            6 -> numPinBtnId = R.id.setting_num_pin6
            5 -> numPinBtnId = R.id.setting_num_pin5
            4 -> numPinBtnId = R.id.setting_num_pin4
            else -> numPinBtnId = R.id.setting_num_pin4
        }
        return numPinBtnId
    }

    private fun saveSettings() {
        val newGameSetting = GameSetting(txtPlayerName.text.toString(), settingNumPin, settingNumColor, settingNumAttempt)
        gameSettingRepository.saveGameSetting(newGameSetting)
        Toast.makeText(baseContext, "Your setting is saved", Toast.LENGTH_SHORT).show()
    }

    private fun resetSettings() {
        val resetGameSetting = GameSetting("MMPlayer", 4, 5, 12)
        gameSettingRepository.saveGameSetting(resetGameSetting)
        Toast.makeText(baseContext, "Game setting is reset", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(
        parent: AdapterView<*>, view: View,
        pos: Int, id: Long
    ) {
        val spinner = parent as Spinner
        if (spinner.id == R.id.setting_num_color) {
            when (pos) {
                0 -> settingNumColor = 4
                1 -> settingNumColor = 5
                2 -> settingNumColor = 6
                3 -> settingNumColor = 7
                4 -> settingNumColor = 8
                5 -> settingNumColor = 9
                else -> settingNumColor = 5
            }
        } else if (spinner.id == R.id.setting_num_attempt) {
            when (pos) {
                0 -> settingNumAttempt = 6
                1 -> settingNumAttempt = 12
                2 -> settingNumAttempt = 18
                3 -> settingNumAttempt = 24
                4 -> settingNumAttempt = 30
                else -> settingNumAttempt = 12
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

}
