package com.andieheung.mastermind_kotlin_mvvm.utils

import android.view.View
import android.widget.Button

fun View.createBtn(handler: View.OnClickListener, id: Int): Button {
    val btn = findViewById<Button>(id)
    btn.setOnClickListener(handler)
    return btn
}