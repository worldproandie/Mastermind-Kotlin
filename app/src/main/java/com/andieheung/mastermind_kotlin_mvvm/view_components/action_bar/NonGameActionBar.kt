package com.andieheung.mastermind_kotlin_mvvm.view_components.action_bar

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils


class NonGameActionBar(activity: AppCompatActivity, handler: View.OnClickListener) :
    BaseActionBar(activity, R.layout.action_bar_base) {
    private var btnHome: Button = MMViewUtils.createBtn(activity, handler, R.id.action_bar_home)
    private var btnRecord: Button = MMViewUtils.createBtn(activity, handler, R.id.action_bar_record)
    private var btnSetting: Button = MMViewUtils.createBtn(activity, handler, R.id.action_bar_setting)
}
