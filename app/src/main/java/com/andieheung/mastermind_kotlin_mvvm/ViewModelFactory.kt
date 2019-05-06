package com.andieheung.mastermind_kotlin_mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andieheung.mastermind_kotlin_mvvm.record.RecordViewModel
import com.andieheung.mastermind_kotlin_mvvm.utils.Injection


class ViewModelFactory private constructor(
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(RecordViewModel::class.java) ->
                    Injection.provideGameAttemptsRepository(context).let { RecordViewModel(it) }

                else ->
                    throw IllegalArgumentException("Invalid ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(context).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}