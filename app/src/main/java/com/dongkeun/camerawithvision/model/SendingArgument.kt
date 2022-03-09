package com.dongkeun.camerawithvision.model

import org.json.JSONObject

class SendingArgument(bytes: ByteArray) {
    var imageBytes: ByteArray? = bytes

    fun toJson(): JSONObject {
        val ret = JSONObject()
        try {
            ret.put("ImageBytes", imageBytes)
        } catch (e: Exception) {
        }

        return ret
    }
}