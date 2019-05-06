package com.andieheung.mastermind_kotlin_mvvm.view_components.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.model.PinColorList
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogHandler

class MMAnsDialogFragment : DialogFragment(), MMBaseDialogFragment {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_show_ans, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setTitleMsg(
            view,
            R.id.dialog_ans_title, title,
            R.id.dialog_ans_msg, msg
        )
        createAnsView(view)
        createBtnOK(view)
    }

    private fun createAnsView(view: View) {
        val ansListView = view.findViewById(R.id.pinlist_ans) as LinearLayout
        val ansArr = ans.pinArray

        for (i in ansArr.indices) {
            val pinImage = ImageView(context)
            val drawableName = ansArr[i].drawableName
            pinImage.setImageResource(getDrawableId(drawableName))
            val pinSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f, resources.displayMetrics).toInt()
            val params = LinearLayout.LayoutParams(pinSize, pinSize)
            pinImage.layoutParams = params
            ansListView.addView(pinImage)
        }
    }

    private fun getDrawableId(drawableName: String): Int {
        val drawableId: Int
        val res = context!!.resources
        drawableId = res.getIdentifier(drawableName, "drawable", context!!.packageName)
        return drawableId
    }

    private fun createBtnOK(view: View) {
        val btn_ok = view.findViewById(R.id.btn_dialog_ans_ok) as Button

        btn_ok.setOnClickListener {
            dismiss()

            if (dialogHandler != null)
                dialogHandler!!.clickOk()
        }
    }

    companion object {
        internal lateinit var title: String
        internal lateinit var msg: String
        internal var dialogHandler: MMDialogHandler? = null
        internal lateinit var ans: PinColorList

        fun initialize(
            ansArr: PinColorList, dTitle: String, dMsg: String,
            dialogHandler: MMDialogHandler
        ): MMAnsDialogFragment {
            title = dTitle
            msg = dMsg
            this.dialogHandler = dialogHandler
            ans = ansArr

            return MMAnsDialogFragment()
        }
    }
}