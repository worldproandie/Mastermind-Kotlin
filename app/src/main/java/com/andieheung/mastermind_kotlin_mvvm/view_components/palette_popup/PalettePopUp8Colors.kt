package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.content.Context
import android.view.View
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.deprecated.GameMVPContract
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment

class PalettePopUp8Colors(context: Context,
                          delegate: ColorPickerFragment) : PalettePopUp(context, delegate) {

    override var btnHandler: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {

            when (v.id) {
                R.id.color_801 -> paletteBtnEvent(1, this)
                R.id.color_802 -> paletteBtnEvent(2, this)
                R.id.color_803 -> paletteBtnEvent(3, this)
                R.id.color_804 -> paletteBtnEvent(4, this)
                R.id.color_805 -> paletteBtnEvent(5, this)
                R.id.color_806 -> paletteBtnEvent(6, this)
                R.id.color_807 -> paletteBtnEvent(7, this)
                R.id.color_808 -> paletteBtnEvent(8, this)
            }
        }
    }

    init {
        view = inflater.inflate(R.layout.color_palette_8, null)
        initColorPaletteBtn(view!!)
    }

    override fun initColorPaletteBtn(pView: View) {

        MMViewUtils.createBtn(pView, btnHandler, R.id.color_801)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_802)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_803)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_804)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_805)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_806)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_807)
        MMViewUtils.createBtn(pView, btnHandler, R.id.color_808)
    }

}

