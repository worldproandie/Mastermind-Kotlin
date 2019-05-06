package com.andieheung.mastermind_kotlin_mvvm.record

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.andieheung.mastermind_kotlin_mvvm.data.game_attempt.GameAttempt
import com.andieheung.mastermind_kotlin_mvvm.databinding.RecordRowBinding

class RecordAdapter(
    private var gAttempts: List<GameAttempt>
) : BaseAdapter() {

    override fun getCount() = gAttempts.size

    override fun getItem(position: Int) = gAttempts[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        val binding: RecordRowBinding
        binding = if (view == null) {

            val inflater = LayoutInflater.from(viewGroup.context)
            RecordRowBinding.inflate(inflater, viewGroup, false)

        } else {
            DataBindingUtil.getBinding(view) ?: throw IllegalStateException()
        }

        with(binding) {
            gameAttempt = gAttempts[position]
            executePendingBindings()
        }

        return binding.root
    }

    fun setList(gAttempts: List<GameAttempt>) {
        this.gAttempts = gAttempts
        notifyDataSetChanged()
    }
}
