package com.andieheung.mastermind_kotlin_mvvm.model

enum class PinColor private constructor(val drawableName: String) {

    Color0("pin00"), Color1("pin01"), Color2("pin02"), Color3("pin03"),
    Color4("pin04"), Color5("pin05"), Color6("pin06"),
    Color7("pin07"), Color8("pin08"), Color9("pin09");

    override fun toString(): String {
        return name
    }

    companion object {

        fun getPinColorByOrdinal(ordinal: Int): PinColor {
            return PinColor.values()[ordinal]
        }
    }
}
