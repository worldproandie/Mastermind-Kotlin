package com.andieheung.mastermind_kotlin_mvvm.data.game_attempt

import androidx.room.*

@Dao
interface GameAttemptsDao {
    @Query("SELECT * FROM game_attempts")
    fun getAll(): List<GameAttempt>

    @Query("SELECT * FROM game_attempts WHERE id LIKE :id")
    fun findById(id: String): GameAttempt

    @Query("SELECT * FROM game_attempts WHERE player_id LIKE :playerId")
    fun findByPlayerId(playerId: String): GameAttempt

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertEntry(gameAttempt: GameAttempt)

    @Delete
    fun delete(gameAttempt: GameAttempt)

    @Query("DELETE FROM game_attempts WHERE id = :id") fun deleteEntry(id: String): Int

    @Query("DELETE FROM game_attempts") fun deleteAll()
}