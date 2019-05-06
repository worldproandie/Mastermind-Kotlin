package com.andieheung.mastermind_kotlin_mvvm.view_components.dialog

import android.view.View
import android.widget.TextView

interface MMBaseDialogFragment {

    fun setTitleMsg(view: View, dwID_title: Int, title: String, dwID_msg: Int, msg: String) {
        val dTitle = view.findViewById<View>(dwID_title) as TextView
        val dMsg = view.findViewById<View>(dwID_msg) as TextView
        dTitle.text = title
        dMsg.text = msg
    }
}