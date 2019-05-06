package com.andieheung.mastermind_kotlin_mvvm.model

enum class GameResultFlag {
    Win, Lose, GivenUp;

    override fun toString(): String {
        return name
    }

    companion object {

        fun getGameResultFlagByOrdinal(ordinal: Int): GameResultFlag {
            return GameResultFlag.values()[ordinal]
        }
    }
}