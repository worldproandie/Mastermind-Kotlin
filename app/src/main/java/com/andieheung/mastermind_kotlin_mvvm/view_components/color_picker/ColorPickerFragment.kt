package com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.andieheung.mastermind_kotlin_mvvm.deprecated.GameMVPContract
import com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup.*

abstract class ColorPickerFragment() : Fragment() {

    protected var cView: View? = null
    protected lateinit var palettePopUp: PalettePopUp
    lateinit var gPresenter: GameMVPContract.GamePresenter
    var gGuessBtnIndex = 0

    abstract val btnHandler: View.OnClickListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val numColor = gPresenter.getNumColor()
        palettePopUp = createPalettePopupInstance(numColor, this)
        palettePopUp.initColorPaletteBtn(palettePopUp.view!!)
    }

    abstract fun colorPickerEnabled(enabled: Boolean)

    protected fun createColorPickerBtn(handler: View.OnClickListener, id: Int): Button {
        val btnGuess = cView!!.findViewById<View>(id) as Button
        btnGuess.setOnClickListener(handler)
        return btnGuess
    }

    protected fun createPalettePopupInstance(num_color: Int,
                                             pickerFragment: ColorPickerFragment): PalettePopUp {
        val instance: PalettePopUp

        when (num_color) {
            4 -> instance = PalettePopUp4Colors(context!!, pickerFragment)
            6 -> instance = PalettePopUp6Colors(context!!, pickerFragment)
            7 -> instance = PalettePopUp7Colors(context!!, pickerFragment)
            8 -> instance = PalettePopUp8Colors(context!!, pickerFragment)
            9 -> instance = PalettePopUp9Colors(context!!, pickerFragment)
            5 -> instance = PalettePopUp5Colors(context!!, pickerFragment)
            else -> instance = PalettePopUp5Colors(context!!, pickerFragment)
        }

        return instance
    }

    override fun getView(): View? {
        return cView
    }

    abstract fun resetColorPickerBtn()
}
