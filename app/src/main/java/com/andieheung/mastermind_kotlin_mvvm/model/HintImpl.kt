package com.andieheung.mastermind_kotlin_mvvm.model

import java.util.ArrayList

class HintImpl : Hint {

    private val id: Long = 0

    private val hintList: MutableList<HintSymbol>

    override val list: List<HintSymbol>
        get() = this.hintList

    init {
        hintList = ArrayList<HintSymbol>()
    }

    override fun add(symbol: HintSymbol) {
        hintList.add(symbol)
    }

    override fun size(): Int {
        return hintList.size
    }

    override fun toString(): String {
        var str: String = "Size:" + hintList.size + " "
        for (i in 1..hintList.size) {
            str += " Pin0" + Integer.toString(i) + ":" + hintList[i - 1].toString()
        }
        return str
    }
}