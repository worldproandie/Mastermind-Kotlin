package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.content.Context
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment

class PalettePopUp7Colors(context: Context,
                          delegate: ColorPickerFragment) : PalettePopUp(context, delegate) {

    override var btnHandler: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {

            when (v.id) {
                R.id.color_701 -> paletteBtnEvent(1, this)
                R.id.color_702 -> paletteBtnEvent(2, this)
                R.id.color_703 -> paletteBtnEvent(3, this)
                R.id.color_704 -> paletteBtnEvent(4, this)
                R.id.color_705 -> paletteBtnEvent(5, this)
                R.id.color_706 -> paletteBtnEvent(6, this)
                R.id.color_707 -> paletteBtnEvent(7, this)
            }
        }
    }

    init {
        view = inflater.inflate(R.layout.color_palette_7, null)
        initColorPaletteBtn(view!!)
    }

    override fun initColorPaletteBtn(pView: View) {

        MMViewUtils.createBtn(pView, btnHandler, R.id.color_701)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_702)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_703)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_704)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_705)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_706)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_707)
    }

}

