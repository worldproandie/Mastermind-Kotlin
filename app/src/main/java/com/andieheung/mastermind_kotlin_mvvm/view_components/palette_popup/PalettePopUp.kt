package com.andieheung.mastermind_kotlin_mvvm.view_components.palette_popup

import android.app.ActionBar
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import com.andieheung.mastermind_kotlin_mvvm.model.PinColor
import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.utils.MMViewUtils
import com.andieheung.mastermind_kotlin_mvvm.view_components.color_picker.ColorPickerFragment
import java.util.HashMap

abstract class PalettePopUp internal constructor(
    protected var context: Context,
    protected var delegate: ColorPickerFragment
) {
    protected var palettePopUp: PopupWindow? = null
    var view: View? = null
        protected set
    protected var inflater: LayoutInflater
    protected abstract val btnHandler: View.OnClickListener


    private var isShown: Boolean = (palettePopUp != null && palettePopUp!!.isShowing)
        get() = (palettePopUp != null && palettePopUp!!.isShowing)

    private val colorPickerBtnId: Int
        get() {

            val num_pin = delegate.gPresenter.getNumPin()
            val index = delegate.gGuessBtnIndex

            return colorPickerBtnIds.get(num_pin)!![index]
        }

    init {
        palettePopUp = PopupWindow()
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun showPalette(anchor: View) {

        palettePopUp!!.height = ActionBar.LayoutParams.WRAP_CONTENT
        palettePopUp!!.width = ActionBar.LayoutParams.WRAP_CONTENT
        palettePopUp!!.isOutsideTouchable = true
        palettePopUp!!.isTouchable = true
        palettePopUp!!.isFocusable = true
        palettePopUp!!.contentView = view

        val pos = IntArray(2)

        anchor.getLocationOnScreen(pos)

        val anchor_rect = Rect(pos[0], pos[1], pos[0] + anchor.width, pos[1] + anchor.height)

        view!!.measure(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )

        val contentViewHeight = view!!.measuredHeight
        val contentViewWidth = view!!.measuredWidth

        val pos_x = anchor_rect.centerX() - (contentViewWidth - contentViewWidth / 2)
        val pos_y = anchor_rect.top - contentViewHeight

        palettePopUp!!.showAtLocation(
            anchor, Gravity.NO_GRAVITY, pos_x,
            pos_y
        )
    }

    fun dismissPalette() {
        if (palettePopUp != null && palettePopUp!!.isShowing) {
            palettePopUp!!.dismiss()
        }
    }

    fun paletteCtrl(v: View) {
        if (!isShown) {
            showPalette(v)
        } else
            dismissPalette()
    }

    abstract fun initColorPaletteBtn(pView: View)


    protected fun updatePickerButton(pinColorOrdinal: Int, btn: Button) {

        val res = context.resources
        val bgDrawable: Drawable?

        val pinColor = PinColor.getPinColorByOrdinal(pinColorOrdinal)
        val drawableName = pinColor.drawableName
        val drawableId = res.getIdentifier(drawableName, "drawable", context.packageName)
        bgDrawable = ResourcesCompat.getDrawable(res, drawableId, null)

        btn.background = bgDrawable
    }


    protected fun paletteBtnEvent(pinColorOrdinal: Int, btn_handler: View.OnClickListener) {

        val index = delegate.gGuessBtnIndex

        delegate.gPresenter.updateCurrentGuess(index, pinColorOrdinal)

        val btnToBeUpdated: Button = MMViewUtils.createBtn(
            delegate.view!!,
            delegate.btnHandler,
            colorPickerBtnId
        )
        updatePickerButton(pinColorOrdinal, btnToBeUpdated)
        dismissPalette()
    }

    companion object {

        private val colorPickerBtnIds: MutableMap<Int, Array<Int>>

        init {
            colorPickerBtnIds = HashMap()
            val colorPicker4 = arrayOf<Int>(R.id.guess_401, R.id.guess_402, R.id.guess_403, R.id.guess_404)
            val colorPicker5 =
                arrayOf<Int>(R.id.guess_501, R.id.guess_502, R.id.guess_503, R.id.guess_504, R.id.guess_505)
            val colorPicker6 = arrayOf<Int>(
                R.id.guess_601,
                R.id.guess_602,
                R.id.guess_603,
                R.id.guess_604,
                R.id.guess_605,
                R.id.guess_606
            )
            colorPickerBtnIds[4] = colorPicker4
            colorPickerBtnIds[5] = colorPicker5
            colorPickerBtnIds[6] = colorPicker6
        }
    }
}
