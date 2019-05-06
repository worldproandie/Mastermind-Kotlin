package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "game_attempts")
data class GameAttempt constructor(
    @ColumnInfo(name = "player_id") val player_id : String = "",
    @ColumnInfo(name = "num_attempted") val num_attempted : Int = 0,
    @ColumnInfo(name = "time_spent") val time_spent : Long = 0,
    @ColumnInfo(name = "result_flag") val result_flag : Int = 0,
    @ColumnInfo(name = "num_pin") val num_pin : Int = 0,
    @ColumnInfo(name = "num_color") val num_color : Int = 0,
    @ColumnInfo(name = "timestamp") val timestamp : OffsetDateTime =  OffsetDateTime.now(),
    @PrimaryKey(autoGenerate = true) val id : Long = 0
)