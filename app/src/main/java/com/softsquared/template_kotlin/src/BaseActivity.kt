package com.softsquared.template_kotlin.src

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.softsquared.template_kotlin.R
import com.softsquared.template_kotlin.utils.SimpleMessageDialog

abstract class BaseActivity(@LayoutRes val layoutId: Int) : AppCompatActivity(), IBaseActivity {

    protected var progressDialog: ProgressDialog? = null
    protected var messageDialog: SimpleMessageDialog? = null

    open fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this, android.R.style.Theme_DeviceDefault_Dialog).apply {
                setMessage(getString(R.string.loading))
                isIndeterminate = true
                setCancelable(false)
            }
        }
        progressDialog!!.show()
    }

    open fun hideProgressDialog() = progressDialog?.apply { if (isShowing) dismiss() }

    open fun showSimpleMessageDialog(
        message: String?,
        btnText: String? = getString(R.string.confirm),
        isCancelable: Boolean = true,
        onClick: ((Dialog) -> Unit)? = null
    ) {
        messageDialog = SimpleMessageDialog(this, message, btnText, isCancelable, onClick)
        messageDialog?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initView()
    }
}

interface IBaseActivity {
    fun initView()
}