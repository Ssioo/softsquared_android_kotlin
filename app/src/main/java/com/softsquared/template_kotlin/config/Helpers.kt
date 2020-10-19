package com.softsquared.template_kotlin.config

import android.content.SharedPreferences
import android.util.Log
import com.orhanobut.logger.Logger
import com.softsquared.template_kotlin.config.Constants.BASE_URL
import com.softsquared.template_kotlin.config.Constants.CONNECT_TIMEOUT
import com.softsquared.template_kotlin.config.Constants.READ_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Helpers {
    lateinit var sharedPreferences: SharedPreferences

    var httpClient = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor {
            if (it.startsWith("{") && it.endsWith("}")) {
                Logger.t("OKHTTP").json(it)
            } else {
                Log.i("OKHTTP", it)
            }
        }.apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .addNetworkInterceptor(XAccessTokenInterceptor())
        .build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}