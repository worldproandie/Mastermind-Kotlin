package com.andieheung.mastermind_kotlin_mvvm.record

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttempt
import com.andieheung.mastermind_kotlin_mvvm.databinding.ActivityRecordBinding
import com.andieheung.mastermind_kotlin_mvvm.menu.MenuActivity
import com.andieheung.mastermind_kotlin_mvvm.setting.SettingActivity
import com.andieheung.mastermind_kotlin_mvvm.utils.getViewModel
import com.andieheung.mastermind_kotlin_mvvm.view_components.action_bar.NonGameActionBar


class RecordActivity : AppCompatActivity(){

    private lateinit var binding :ActivityRecordBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityRecordBinding>(this, R.layout.activity_record).apply{
            gameRecordList.adapter = RecordAdapter(ArrayList(0))
            viewModel = getViewModel(RecordViewModel::class.java)
        }

        binding.viewModel?.items?.observe(this@RecordActivity, object : Observer<List<GameAttempt>> {
            override fun onChanged(list : List<GameAttempt>){
                RecordListBindings.setItems(binding.gameRecordList, list)
            }
        })

        val actionBar = NonGameActionBar(this@RecordActivity, btnHandlerActionBar)
    }

    private var btnHandlerActionBar: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.action_bar_home -> { startActivity(Intent(applicationContext, MenuActivity::class.java)) }

            R.id.action_bar_record -> { }

            R.id.action_bar_setting -> { startActivity(Intent(applicationContext, SettingActivity::class.java)) }
        }
    }

    public override fun onResume() {
        super.onResume()
        binding.apply { viewModel?.getAll() }
    }
}