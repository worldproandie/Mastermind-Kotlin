package com.andieheung.mastermind_kotlin_mvvm.data.game_setting

data class GameSetting constructor(
     var playerName : String = "MMPlayer",
     var numPin : Int = 4,
     var numColor : Int = 5,
     var numAttempt : Int = 12
     )