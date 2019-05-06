package com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.andieheung.mastermind_kotlin_mvvm.R

class ColorPickerFragment5Pins : ColorPickerFragment() {

    private var btnGuess01: Button? = null
    private var btnGuess02: Button? = null
    private var btnGuess03: Button? = null
    private var btnGuess04: Button? = null
    private var btnGuess05: Button? = null

    override val btnHandler: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.guess_501 -> {
                gGuessBtnIndex = 0
                palettePopUp.paletteCtrl(v)
            }
            R.id.guess_502 -> {
                gGuessBtnIndex = 1
                palettePopUp.paletteCtrl(v)
            }
            R.id.guess_503 -> {
                gGuessBtnIndex = 2
                palettePopUp.paletteCtrl(v)
            }
            R.id.guess_504 -> {
                gGuessBtnIndex = 3
                palettePopUp.paletteCtrl(v)
            }
            R.id.guess_505 -> {
                gGuessBtnIndex = 4
                palettePopUp.paletteCtrl(v)
            }
            else -> gGuessBtnIndex = 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cView = inflater.inflate(R.layout.player_color_guess_5, container, false)
        return cView!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initColorPickerBtn()
        colorPickerEnabled(false)
    }

    private fun initColorPickerBtn() {
        btnGuess01 = createColorPickerBtn(btnHandler, R.id.guess_501)
        btnGuess02 = createColorPickerBtn(btnHandler, R.id.guess_502)
        btnGuess03 = createColorPickerBtn(btnHandler, R.id.guess_503)
        btnGuess04 = createColorPickerBtn(btnHandler, R.id.guess_504)
        btnGuess05 = createColorPickerBtn(btnHandler, R.id.guess_505)
    }

    override fun colorPickerEnabled(enabled: Boolean) {
        btnGuess01?.isEnabled = enabled
        btnGuess02?.isEnabled = enabled
        btnGuess03?.isEnabled = enabled
        btnGuess04?.isEnabled = enabled
        btnGuess05?.isEnabled = enabled
    }

    override fun resetColorPickerBtn() {
        btnGuess01?.setBackgroundResource(R.drawable.pin00)
        btnGuess02?.setBackgroundResource(R.drawable.pin00)
        btnGuess03?.setBackgroundResource(R.drawable.pin00)
        btnGuess04?.setBackgroundResource(R.drawable.pin00)
        btnGuess05?.setBackgroundResource(R.drawable.pin00)
    }

}
