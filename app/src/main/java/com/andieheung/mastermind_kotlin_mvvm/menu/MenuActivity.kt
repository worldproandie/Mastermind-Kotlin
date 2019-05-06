package com.andieheung.mastermind_kotlin_mvvm.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.game.GameActivity
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.record.RecordActivity
import com.andieheung.mastermind_kotlin_mvvm.setting.SettingActivity
import com.andieheung.mastermind_kotlin_mvvm.utils.createBtn

class MenuActivity : AppCompatActivity() {


    private var btnHandler: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.start_btn -> startActivity(Intent(applicationContext, GameActivity::class.java))
            R.id.rank_btn -> startActivity(Intent(applicationContext, RecordActivity::class.java))
            R.id.set_btn -> startActivity(Intent(applicationContext, SettingActivity::class.java))
            else -> {
            }
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btn_start = createBtn(btnHandler, R.id.start_btn)
        val btn_rank = createBtn(btnHandler, R.id.rank_btn)
        val btn_set = createBtn(btnHandler, R.id.set_btn)
    }
}
