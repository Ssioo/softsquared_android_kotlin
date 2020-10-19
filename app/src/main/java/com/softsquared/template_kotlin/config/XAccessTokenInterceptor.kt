package com.softsquared.template_kotlin.config

import com.softsquared.template_kotlin.config.Constants.X_ACCESS_TOKEN
import com.softsquared.template_kotlin.config.Helpers.sharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class XAccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        sharedPreferences.getString(X_ACCESS_TOKEN, null)?.let {
            builder.addHeader(X_ACCESS_TOKEN, it)
        }
        return chain.proceed(builder.build())
    }
}