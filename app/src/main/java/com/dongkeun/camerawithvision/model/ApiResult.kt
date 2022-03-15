package com.dongkeun.camerawithvision.model

import com.google.gson.annotations.SerializedName

data class ApiResult(
    @field:SerializedName("ResultCode")
    val resultCode: Int,
    @field:SerializedName("ResultMessage")
    val resultMessage: String,
    @field:SerializedName("Data")
    val data: Any
)