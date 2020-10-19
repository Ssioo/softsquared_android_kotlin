package com.softsquared.template_kotlin.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import com.softsquared.template_kotlin.R
import kotlinx.android.synthetic.main.dialog_posneg.*

class PosNegDialog(
    context: Context,
    val message: String = "",
    val cancelable: Boolean = true,
    val txtPos: String = context.getString(R.string.positive),
    val txtNeg: String = context.getString(R.string.negative),
    val onClick: ((Dialog, Int) -> Unit)? = null
) : Dialog(context) {

    init {
        setCancelable(cancelable)
    }

    companion object {
        const val BUTTON_POSITIVE = 0
        const val BUTTON_NEGATIVE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_posneg)

        tv_desc_dialog_posneg.text = message
        tv_positive_dialog_posneg.apply {
            text = txtPos
            setOnClickListener { onClick?.invoke(this@PosNegDialog, BUTTON_POSITIVE) }
        }
        tv_negative_dialog_posneg.apply {
            text = txtNeg
            setOnClickListener { onClick?.invoke(this@PosNegDialog, BUTTON_NEGATIVE) }
        }

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.CENTER)
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val dialogBounds = Rect().apply { cl_dialog_posneg.getHitRect(this) }
        if (cancelable && !dialogBounds.contains(ev.x.toInt(), ev.y.toInt())) dismiss()
        return super.dispatchTouchEvent(ev)
    }
}