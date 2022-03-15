package com.dongkeun.camerawithvision.dashboard

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongkeun.camerawithvision.model.ApiResult
import com.dongkeun.camerawithvision.model.Post
import com.dongkeun.camerawithvision.utility.KtorHttp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import org.json.JSONObject
import timber.log.Timber

class DashboardViewModel : ViewModel() {
    private var listFromServer = mutableListOf<Post>()
    private val _postList = MutableLiveData<MutableList<Post>>()
    val postList: LiveData<MutableList<Post>> get() = _postList
    private val httpClient = KtorHttp { handleNetworkingError() }
    private val gson = Gson()
    private var job: Job? = null
    val progressBarVisible = MutableLiveData<Boolean>()

    init {
        progressBarVisible.value = false
        listFromServer = mutableListOf()
        _postList.value = mutableListOf()
    }

    private fun convertBytesToBitmap() {
        if (listFromServer.isEmpty()) return

        val charSet = Charsets.UTF_8
        listFromServer.forEach {
            val bytes = it.photoBytes.toByteArray(charSet)
            val postImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            it.bitmap = postImage
        }

        _postList.value = listFromServer
    }

    fun getPostsFromServer() {
        progressBarVisible.value = true
        job = CoroutineScope(Job() + Dispatchers.IO)
            .launch {
                val getListResult = getPostsFromServerSuspend()
                if (getListResult == null) {
                    handleNetworkingError()
                    return@launch
                }

                val type = object : TypeToken<MutableList<Post>>(){}.type
                _postList.postValue(gson.fromJson(
                    gson.toJsonTree(getListResult.data).asJsonArray, type
                ))
                withContext(Dispatchers.Main) { progressBarVisible.value = false }
            }
    }

    private suspend fun getPostsFromServerSuspend(): ApiResult? {
        return httpClient.setHttpRequest("to_do_url", JSONObject("to_do"))
    }

    fun addFavoritePost() {

    }

    private fun handleNetworkingError() {
        Timber.i("Networking failed.")
        job?.cancel()
    }
}