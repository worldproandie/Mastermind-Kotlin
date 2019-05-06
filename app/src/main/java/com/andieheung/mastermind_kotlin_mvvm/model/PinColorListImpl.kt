package com.andieheung.mastermind_kotlin_mvvm.model

import java.util.*

class PinColorListImpl(length: Int): PinColorList {

    private val pinColorList: MutableList<PinColor>

    override val copyList: MutableList<PinColor>
        get() = ArrayList<PinColor>(this.pinColorList)

    override val pinArray: Array<PinColor>
        get() = this.pinColorList.toTypedArray() as Array<PinColor>

    override val isComplete: Boolean
        get() = !this.pinColorList.contains(PinColor.Color0)

    init {
        pinColorList = Arrays.asList<PinColor>(*arrayOfNulls<PinColor>(length))
        for (i in 0 until length) {
            pinColorList[i] = PinColor.Color0
        }
    }

    override fun setPin(pos: Int, pinColorOrdinal: Int) {
        val pinColor = PinColor.getPinColorByOrdinal(pinColorOrdinal)
        this.pinColorList[pos] = pinColor
    }

    override fun allColorMatches(pArrObj: PinColorList): Boolean {
        return Arrays.equals(this.pinColorList.toTypedArray(), pArrObj.pinArray)
    }

    override fun toString(): String {
        var str: String
        str = "Size:" + this.pinColorList.size + " "
        str += " [ "
        for (i in this.pinColorList.indices) {
            str += this.pinColorList[i].toString()
            if (i < this.pinColorList.size - 1)
                str += ", "
        }
        str += " ] "
        return str
    }
}
