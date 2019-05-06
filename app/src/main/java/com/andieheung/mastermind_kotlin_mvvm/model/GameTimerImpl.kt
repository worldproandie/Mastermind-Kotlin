package com.andieheung.mastermind_kotlin_mvvm.model

import android.os.Handler

class GameTimerImpl private constructor(): GameTimer{

    companion object {

        @Volatile private var INSTANCE: GameTimerImpl? = null

        fun getInstance(): GameTimerImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameTimerImpl().also { INSTANCE = it }
            }

    }

    private var handler: Handler? = null
    private var runnable : Runnable? = null

    override fun init(gTask : () -> Unit) {

        if (handler == null)
            handler = Handler()

        if (runnable == null){
            runnable = Runnable(){
                gTask()
                handler?.postDelayed(runnable, 1000)
            }
        }
    }

    override fun pause() {
        if (handler != null && runnable != null){
            handler?.removeCallbacks(runnable)
        }
    }

    override fun resume() {
        handler?.post(runnable)
    }

    override fun end() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable)
            handler = null
            runnable = null
        }
    }
}