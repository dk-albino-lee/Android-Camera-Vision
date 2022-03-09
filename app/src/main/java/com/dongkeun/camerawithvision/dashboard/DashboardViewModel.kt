package com.dongkeun.camerawithvision.dashboard

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongkeun.camerawithvision.model.Post
import com.google.gson.Gson

class DashboardViewModel : ViewModel() {
    private var listFromServer = mutableListOf<Post>()
    private val _postList = MutableLiveData<MutableList<Post>>()
    val postList: LiveData<MutableList<Post>> get() = _postList

    val gson = Gson()

    init {
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

    fun addFavoritePost() {

    }
}