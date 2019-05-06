package com.andieheung.mastermind_kotlin_mvvm.view_components.action_bar

import android.app.Activity
import android.content.Context
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup

open class BaseActionBar(context: Context, @LayoutRes resource: Int) {

    init {

        val actionBarLayout = (context as Activity).layoutInflater.inflate(
            resource, null
        ) as ViewGroup

        val actionBar = (context as AppCompatActivity).supportActionBar
        try {

            actionBar!!.setDisplayShowHomeEnabled(false)
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.customView = actionBarLayout

        } catch (e: Exception) {
            Log.e(this.javaClass.toString(), e.message)
        }

    }
}