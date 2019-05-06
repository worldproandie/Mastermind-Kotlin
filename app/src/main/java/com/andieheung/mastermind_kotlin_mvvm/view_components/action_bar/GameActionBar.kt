package com.andieheung.mastermind_kotlin_mvvm.view_components.action_bar

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.andieheung.mastermind_kotlin_mvvm.R

class GameActionBar(activity: AppCompatActivity, clickListener: View.OnClickListener) :
    BaseActionBar(activity, R.layout.action_bar_game) {

    private var btnHome: Button = activity.findViewById(R.id.action_bar_home_game) as Button
    private var btnHelp: Button = activity.findViewById(R.id.action_bar_help_game) as Button

    init {

        btnHome.setOnClickListener(clickListener)
        btnHelp.setOnClickListener(clickListener)
    }
}