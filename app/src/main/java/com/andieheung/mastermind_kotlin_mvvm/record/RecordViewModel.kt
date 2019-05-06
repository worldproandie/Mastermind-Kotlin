package com.andieheung.mastermind_kotlin_mvvm.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttempt
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttemptsDataSource
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttemptsRepository

class RecordViewModel (
    private val gameAttemptsRepository: GameAttemptsRepository
) : ViewModel(){
    private val _items = MutableLiveData<List<GameAttempt>>().apply { value = emptyList() }
    val items: LiveData<List<GameAttempt>>
        get() = _items

    private var isLoadingError = false

    fun getAll() {

        gameAttemptsRepository.getAll(object : GameAttemptsDataSource.LoadGameAttemptsCallback {

            override fun onEntriesLoaded(gameAttempts: List<GameAttempt>) {
                _items.value = gameAttempts
                Log.d("recordVM", "on entries loaded : " + (_items.value).toString())
            }

            override fun onDataNotAvailable() {
                isLoadingError = true
                Log.d("recordVM", "on data not available : " +(_items.value).toString())
            }
        })
    }
}

