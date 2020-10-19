package com.softsquared.template_kotlin.src.main.networks

import com.softsquared.template_kotlin.src.BaseResponse
import com.softsquared.template_kotlin.src.main.models.TestData
import retrofit2.Call
import retrofit2.http.GET

interface MainApi {

    @GET("/test")
    fun getTest(): Call<BaseResponse<TestData>>
}