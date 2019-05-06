package com.andieheung.mastermind_kotlin_mvvm.view_components.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler.MMDialogCancellableHandler

class MMDialogFragment : DialogFragment(), MMBaseDialogFragment {

    override fun onCancel(dialog: DialogInterface?) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_normal_2btn, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setTitleMsg(
            view,
            R.id.dialog_normal_title, title,
            R.id.dialog_normal_msg, msg
        )
        createBtnOK(view)
        createBtnCancel(view)
    }

    private fun createBtnOK(view: View) {
        val btnOk = view.findViewById(R.id.btn_dialog_normal_ok) as Button

        btnOk.setOnClickListener {
            dismiss()

            val delayHandler = Handler()
            delayHandler.postDelayed({
                if (dialogHandler != null)
                    dialogHandler!!.clickOk()
            }, 200)
        }
    }

    private fun createBtnCancel(view: View) {
        val btn_cancel = view.findViewById(R.id.btn_dialog_normal_cancel) as Button

        btn_cancel.setOnClickListener {
            dismiss()
            if (dialogHandler != null)
                dialogHandler!!.clickCancel()
        }
    }

    companion object {
        internal lateinit var title: String
        internal lateinit var msg: String
        internal var dialogHandler: MMDialogCancellableHandler? = null

        fun initialize(
            dTitle: String, dMsg: String,
            dialogHandler: MMDialogCancellableHandler
        ): MMDialogFragment {
            title = dTitle
            msg = dMsg
            this.dialogHandler = dialogHandler

            return MMDialogFragment()
        }
    }
}
