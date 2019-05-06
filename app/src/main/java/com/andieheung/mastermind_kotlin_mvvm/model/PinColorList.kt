package com.andieheung.mastermind_kotlin_mvvm.model

interface PinColorList {
    val copyList: MutableList<PinColor>
    val pinArray: Array<PinColor>
    val isComplete: Boolean

    fun setPin(pos: Int, pinColorOrdinal: Int)
    fun allColorMatches(pArrObj: PinColorList): Boolean
}