package com.softsquared.template_kotlin.src.main

import com.orhanobut.logger.Logger
import com.softsquared.template_kotlin.R
import com.softsquared.template_kotlin.src.BaseActivity
import com.softsquared.template_kotlin.src.main.networks.MainService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initView() {
        tryFetchTestData()
    }

    fun tryFetchTestData() {
        MainService().fetchTestData({
            Logger.d(it)
            tv_test.text = "Success!"
        }, {
            showToast(it ?: getString(R.string.network_error))
        })
    }
}