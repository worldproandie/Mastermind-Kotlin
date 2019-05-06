package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.content.Context
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment

class PalettePopUp5Colors(context: Context,
                          delegate: ColorPickerFragment
) : PalettePopUp(context, delegate) {

    override val btnHandler: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {

            when (v.id) {
                R.id.color_501 -> paletteBtnEvent(1, this)
                R.id.color_502 -> paletteBtnEvent(2, this)
                R.id.color_503 -> paletteBtnEvent(3, this)
                R.id.color_504 -> paletteBtnEvent(4, this)
                R.id.color_505 -> paletteBtnEvent(5, this)
            }
        }
    }

    init {
        view = inflater.inflate(R.layout.color_palette_5, null)
        initColorPaletteBtn(view!!)
    }

    override fun initColorPaletteBtn(pView: View) {

        MMViewUtils.createBtn(pView, btnHandler, R.id.color_501)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_503)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_504)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_502)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_505)
    }

}
