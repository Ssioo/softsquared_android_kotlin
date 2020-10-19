package com.softsquared.template_kotlin.src.main.networks

import com.softsquared.template_kotlin.config.Helpers
import com.softsquared.template_kotlin.src.BaseResponse
import com.softsquared.template_kotlin.src.main.models.TestData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainService {
    fun fetchTestData(onSuccess: (TestData?) -> Unit, onFailure: (String?) -> Unit) {
        Helpers.retrofit.create(MainApi::class.java)
            .getTest().enqueue(object : Callback<BaseResponse<TestData>> {
                override fun onResponse(
                    call: Call<BaseResponse<TestData>>,
                    response: Response<BaseResponse<TestData>>
                ) {
                    response.body()?.let {
                        if (it.isSuccess) onSuccess(it.result)
                        else onFailure(it.message)
                    } ?: onFailure(null)
                }

                override fun onFailure(call: Call<BaseResponse<TestData>>, t: Throwable) {
                    t.printStackTrace()
                    onFailure(null)
                }
            })
    }
}