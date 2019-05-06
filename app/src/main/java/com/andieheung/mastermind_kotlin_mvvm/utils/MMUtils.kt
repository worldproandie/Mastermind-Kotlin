package com.andieheung.mastermind_kotlin_mvvm.utils

import java.text.SimpleDateFormat

object MMUtils {

    fun formatTime(millis: Long): String {
        val sdf = SimpleDateFormat("mm:ss")

        return sdf.format(millis)
    }
}