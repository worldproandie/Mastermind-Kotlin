package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.content.Context
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.deprecated.GameMVPContract
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment

class PalettePopUp9Colors(context: Context,
                          delegate: ColorPickerFragment) : PalettePopUp(context, delegate) {

    override var btnHandler: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {

            when (v.id) {
                R.id.color_901 -> paletteBtnEvent(1, this)
                R.id.color_902 -> paletteBtnEvent(2, this)
                R.id.color_903 -> paletteBtnEvent(3, this)
                R.id.color_904 -> paletteBtnEvent(4, this)
                R.id.color_905 -> paletteBtnEvent(5, this)
                R.id.color_906 -> paletteBtnEvent(6, this)
                R.id.color_907 -> paletteBtnEvent(7, this)
                R.id.color_908 -> paletteBtnEvent(8, this)
                R.id.color_909 -> paletteBtnEvent(9, this)
            }
        }
    }

    init {
        view = inflater.inflate(R.layout.color_palette_9, null)
        initColorPaletteBtn(view!!)
    }

    override fun initColorPaletteBtn(pView: View) {

        MMViewUtils.createBtn(pView, btnHandler, R.id.color_901)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_902)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_903)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_904)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_905)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_906)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_907)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_908)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_909)
    }

}