package com.andieheung.mastermind_kotlin_mvvm.record


import androidx.databinding.BindingAdapter
import android.widget.ListView
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttempt
import android.widget.TextView
import com.andieheung.mastermind_kotlin_mvvm.model.GameResultFlag
import com.andieheung.mastermind_kotlin_mvvm.utils.MMUtils


object RecordListBindings {

    @BindingAdapter("items")
    @JvmStatic fun setItems(listView: ListView, items: List<GameAttempt>) {
        with(listView.adapter as RecordAdapter) {
            setList(items)
        }
    }

    @BindingAdapter("time_spent")
    @JvmStatic fun setTimeSpent(view: TextView, time_spent: Long) {
        view.text = MMUtils.formatTime(time_spent)
    }

    @BindingAdapter("result_flag")
    @JvmStatic fun setResultFlag(view: TextView, result_flag: Int) {
        view.text = GameResultFlag.getGameResultFlagByOrdinal(result_flag).name
    }
}
