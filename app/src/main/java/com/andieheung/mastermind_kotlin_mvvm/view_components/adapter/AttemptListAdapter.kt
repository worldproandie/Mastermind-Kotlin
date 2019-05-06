package com.andieheung.mastermind_kotlin_mvvm.view_components.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.model.Hint
import com.andieheung.mastermind_kotlin_mvvm.model.PinColorList
import java.util.ArrayList

class AttemptListAdapter(internal var context: Context) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var viewHolder: ViewHolder
    private var numPin = 4
    private lateinit var guessAttemptList: ArrayList<PinColorList>
    private lateinit var hintAttemptList: ArrayList<Hint>

    init {
        this.layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun setGameData(num: Int, guessList: ArrayList<PinColorList>, hintList: ArrayList<Hint>) {
        this.numPin = num
        this.guessAttemptList = guessList
        this.hintAttemptList = hintList
    }

    override fun getCount(): Int {
        return guessAttemptList.size
    }

    override fun getItem(pos: Int): Any {
        return guessAttemptList[pos]
    }

    override fun getItemId(pos: Int): Long {
        return 0
    }

    private fun getHintItem(pos: Int): Hint {
        return hintAttemptList[pos]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        if (convertView == null) {
            convertView = createConvertView(parent)
            viewHolder = createViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.itemIndexTxt.text = Integer.toString(position + 1)

        val pArr = getItem(position) as PinColorList
        val pinArrRow = pArr.pinArray
        val hList = getHintItem(position)
        val hArrayList = hList.list

        for (i in 0 until numPin) {
            var drawableName = pinArrRow[i].drawableName
            viewHolder.guessView!![i].setImageResource(getDrawableId(drawableName))
            drawableName = hArrayList.get(i).drawableName
            viewHolder.hintView!![i].setImageResource(getDrawableId(drawableName))
        }

        return convertView
    }

    private fun getDrawableId(drawableName: String): Int {
        val drawableId: Int
        val res = context.resources
        drawableId = res.getIdentifier(drawableName, "drawable", context.packageName)
        return drawableId
    }

    private fun createConvertView(parent: ViewGroup): View {
        val convertView: View
        when (numPin) {
            6 -> convertView = layoutInflater!!.inflate(R.layout.attempt_row_6, parent, false)
            5 -> convertView = layoutInflater!!.inflate(R.layout.attempt_row_5, parent, false)
            4 -> convertView = layoutInflater!!.inflate(R.layout.attempt_row_4, parent, false)
            else -> convertView = layoutInflater!!.inflate(R.layout.attempt_row_4, parent, false)
        }
        return convertView
    }

    private fun createViewHolder(view: View): ViewHolder {
        val viewHolder: ViewHolder
        when (numPin) {
            6 -> viewHolder = ViewHolder6pins(view)
            5 -> viewHolder = ViewHolder5pins(view)
            4 -> viewHolder = ViewHolder4pins(view)
            else -> viewHolder = ViewHolder4pins(view)
        }
        return viewHolder
    }

    private abstract inner class ViewHolder(view: View) {
        internal var itemIndexTxt: TextView = view.findViewById(R.id.item_index_txt) as TextView
        internal var guessView: Array<ImageView>? = null
        internal var hintView: Array<ImageView>? = null
    }

    private inner class ViewHolder4pins(view: View) : ViewHolder(view) {
        internal var guess401: ImageView = view.findViewById<ImageView>(R.id.guess_row_401)
        internal var guess402: ImageView = view.findViewById<ImageView>(R.id.guess_row_402)
        internal var guess403: ImageView = view.findViewById<ImageView>(R.id.guess_row_403)
        internal var guess404: ImageView = view.findViewById<ImageView>(R.id.guess_row_404)
        internal var hint401: ImageView = view.findViewById<ImageView>(R.id.hint_row_401)
        internal var hint402: ImageView = view.findViewById<ImageView>(R.id.hint_row_402)
        internal var hint403: ImageView = view.findViewById<ImageView>(R.id.hint_row_403)
        internal var hint404: ImageView = view.findViewById<ImageView>(R.id.hint_row_404)

        init {
            guessView = arrayOf(guess401, guess402, guess403, guess404)
            hintView = arrayOf(hint401, hint402, hint403, hint404)
        }
    }

    private inner class ViewHolder5pins(view: View) : ViewHolder(view) {
        internal var guess501: ImageView = view.findViewById<ImageView>(R.id.guess_row_501)
        internal var guess502: ImageView = view.findViewById<ImageView>(R.id.guess_row_502)
        internal var guess503: ImageView = view.findViewById<ImageView>(R.id.guess_row_503)
        internal var guess504: ImageView = view.findViewById<ImageView>(R.id.guess_row_504)
        internal var guess505: ImageView = view.findViewById<ImageView>(R.id.guess_row_505)
        internal var hint501: ImageView = view.findViewById<ImageView>(R.id.hint_row_501)
        internal var hint502: ImageView = view.findViewById<ImageView>(R.id.hint_row_502)
        internal var hint503: ImageView = view.findViewById<ImageView>(R.id.hint_row_503)
        internal var hint504: ImageView = view.findViewById<ImageView>(R.id.hint_row_504)
        internal var hint505: ImageView = view.findViewById<ImageView>(R.id.hint_row_505)

        init {
            guessView = arrayOf(guess501, guess502, guess503, guess504, guess505)
            hintView = arrayOf(hint501, hint502, hint503, hint504, hint505)
        }
    }

    private inner class ViewHolder6pins(view: View) : ViewHolder(view) {
        internal var guess601: ImageView = view.findViewById<ImageView>(R.id.guess_row_601)
        internal var guess602: ImageView = view.findViewById<ImageView>(R.id.guess_row_602)
        internal var guess603: ImageView = view.findViewById<ImageView>(R.id.guess_row_603)
        internal var guess604: ImageView = view.findViewById<ImageView>(R.id.guess_row_604)
        internal var guess605: ImageView = view.findViewById<ImageView>(R.id.guess_row_605)
        internal var guess606: ImageView = view.findViewById<ImageView>(R.id.guess_row_606)
        internal var hint601: ImageView = view.findViewById<ImageView>(R.id.hint_row_601)
        internal var hint602: ImageView = view.findViewById<ImageView>(R.id.hint_row_602)
        internal var hint603: ImageView = view.findViewById<ImageView>(R.id.hint_row_603)
        internal var hint604: ImageView = view.findViewById<ImageView>(R.id.hint_row_604)
        internal var hint605: ImageView = view.findViewById<ImageView>(R.id.hint_row_605)
        internal var hint606: ImageView = view.findViewById<ImageView>(R.id.hint_row_606)

        init {
            guessView = arrayOf(guess601, guess602, guess603, guess604, guess605, guess606)
            hintView = arrayOf(hint601, hint602, hint603, hint604, hint605, hint606)
        }
    }
}
