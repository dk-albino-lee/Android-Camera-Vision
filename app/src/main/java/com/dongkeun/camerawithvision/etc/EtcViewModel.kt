package com.dongkeun.camerawithvision.etc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class EtcViewModel : ViewModel() {
    val childFragmentVisible = MutableLiveData<Boolean>()

    init {
        childFragmentVisible.value = false
    }

    private fun setChildFragment() {
        childFragmentVisible.value = true
    }

    fun setBlockClickEvent() {
        Timber.i("block clicked")
    }
}