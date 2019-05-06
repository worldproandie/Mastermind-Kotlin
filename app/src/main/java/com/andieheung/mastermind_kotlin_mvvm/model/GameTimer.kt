package com.andieheung.mastermind_kotlin_mvvm.model

interface GameTimer {

    fun init(gTask : () -> Unit)
    fun pause()
    fun resume()
    fun end()

}