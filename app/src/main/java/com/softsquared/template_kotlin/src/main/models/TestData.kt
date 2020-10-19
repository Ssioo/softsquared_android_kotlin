package com.softsquared.template_kotlin.src.main.models

import com.google.gson.annotations.SerializedName

data class TestData (
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: Int
)