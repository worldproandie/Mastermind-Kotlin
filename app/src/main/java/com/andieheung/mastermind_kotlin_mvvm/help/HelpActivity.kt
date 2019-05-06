package com.andieheung.mastermind_kotlin_mvvm.help

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.utils.createBtn

class HelpActivity : AppCompatActivity() {

    private var btnHandler: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.bk_to_game -> finish()
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        val btn_bk = createBtn(btnHandler, R.id.bk_to_game)
    }
}
