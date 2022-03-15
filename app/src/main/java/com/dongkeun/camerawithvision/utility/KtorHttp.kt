package com.dongkeun.camerawithvision.utility

import com.dongkeun.camerawithvision.model.ApiResult
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.observer.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import timber.log.Timber

class KtorHttp constructor(val errorHandling: () -> Unit) {
    private lateinit var client: HttpClient

    fun newInstance(): KtorHttp {
        val ret = KtorHttp(errorHandling)
        ret.client = HttpClient(CIO) {
            expectSuccess = false
            ResponseObserver { response ->
                if (response.status.value in 300..599) {
                    errorHandling()
                }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 10000L
            }
            install(JsonFeature) {
                serializer = GsonSerializer() {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }
        }

        return ret
    }

    suspend fun setHttpRequest(_url: String, reqData: Any): ApiResult? {
        val url = StaticValue.baseUrl + _url
        Timber.i("request url: $url")
        Timber.i("request body: $reqData")
        val response: ApiResult = client.post(url) {
            contentType(ContentType.Application.Json)
            body = reqData
        }
        if (!handleApiResult(response)) return null
        return response
    }

    private fun handleApiResult(response: ApiResult): Boolean {
        Timber.i("response: $response")
        if (response.resultCode < 1) {
            errorHandling()
            return false
        }
        return true
    }
}