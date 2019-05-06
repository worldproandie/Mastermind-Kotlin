package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.content.Context
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.deprecated.GameMVPContract
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment

class PalettePopUp6Colors(context: Context,
                          delegate: ColorPickerFragment) : PalettePopUp(context, delegate) {

    override var btnHandler: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {

            when (v.id) {
                R.id.color_601 -> paletteBtnEvent(1, this)
                R.id.color_602 -> paletteBtnEvent(2, this)
                R.id.color_603 -> paletteBtnEvent(3, this)
                R.id.color_604 -> paletteBtnEvent(4, this)
                R.id.color_605 -> paletteBtnEvent(5, this)
                R.id.color_606 -> paletteBtnEvent(6, this)
            }
        }
    }

    init {
        view = inflater.inflate(R.layout.color_palette_6, null)
        initColorPaletteBtn(view!!)
    }

    override fun initColorPaletteBtn(pView: View) {

        MMViewUtils.createBtn(pView, btnHandler, R.id.color_601)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_602)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_603)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_604)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_605)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_606)
    }

}

