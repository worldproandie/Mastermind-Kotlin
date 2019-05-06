package com.andieheung.mastermind_kotlin_mvvm.model

enum class HintSymbol private constructor(val drawableName: String) {
    Match("hint1"), ColorOnly("hint2"), NotMatch("hint3");

    override fun toString(): String {
        return name
    }
}