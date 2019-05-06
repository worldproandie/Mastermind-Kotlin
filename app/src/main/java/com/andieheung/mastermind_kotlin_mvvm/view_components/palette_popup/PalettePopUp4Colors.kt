package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.content.Context
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment

class PalettePopUp4Colors(context: Context,
                          delegate: ColorPickerFragment) : PalettePopUp(context, delegate) {

    override val btnHandler: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {

            when (v.id) {
                R.id.color_401 -> paletteBtnEvent(1, this)
                R.id.color_402 -> paletteBtnEvent(2, this)
                R.id.color_403 -> paletteBtnEvent(3, this)
                R.id.color_404 -> paletteBtnEvent(4, this)
            }
        }
    }

    init {
        view = inflater.inflate(R.layout.color_palette_4, null)
        initColorPaletteBtn(view!!)
    }

    override fun initColorPaletteBtn(pView: View) {

        MMViewUtils.createBtn(pView, btnHandler, R.id.color_401)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_402)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_403)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_404)
    }

}
