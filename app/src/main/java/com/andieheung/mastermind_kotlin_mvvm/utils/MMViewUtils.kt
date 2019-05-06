package com.andieheung.mastermind_kotlin_mvvm.utils

import android.app.Activity
import android.view.View
import android.widget.*


object MMViewUtils {

    fun createBtn(a: Activity, handler: View.OnClickListener, id: Int): Button {
        val btn = a.findViewById<Button>(id)
        btn.setOnClickListener(handler)
        return btn
    }

    fun createBtn(v: View, handler: View.OnClickListener, id: Int): Button {
        val btn = v.findViewById<Button>(id)
        btn.setOnClickListener(handler)
        return btn
    }
}
