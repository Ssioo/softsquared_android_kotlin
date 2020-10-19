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
import kotlinx.android.synthetic.main.dialog_simple_message.*

class SimpleMessageDialog(
    context: Context,
    val message: String? = "",
    val btnText: String? = "",
    val cancleable: Boolean = true,
    val onClick: ((Dialog) -> Unit)? = null
) : Dialog(context) {

    init {
        setCancelable(cancleable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_simple_message)

        tv_desc_dialog_simple_message.text = message
        tv_positive_dialog_simple_message.apply {
            text = btnText
            setOnClickListener { onClick?.invoke(this@SimpleMessageDialog) ?: dismiss() }
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
        val dialogBounds = Rect().apply { cl_dialog_simple_message.getHitRect(this) }
        if (cancleable && !dialogBounds.contains(ev.x.toInt(), ev.y.toInt())) dismiss()
        return super.dispatchTouchEvent(ev)
    }
}