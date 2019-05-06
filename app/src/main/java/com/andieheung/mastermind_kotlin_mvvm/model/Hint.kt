package com.andieheung.mastermind_kotlin_mvvm.model

interface Hint {

    val list: List<HintSymbol>

    fun add(symbol: HintSymbol)
    fun size(): Int

}