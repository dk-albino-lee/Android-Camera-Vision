package com.dongkeun.camerawithvision.model

import com.google.gson.annotations.SerializedName

data class DetectedFeature(
    @field:SerializedName("ImageBytes")
    val imageBytes: ByteArray? = null,
    @field:SerializedName("Labels")
    val labels: Array<String>? = null
)