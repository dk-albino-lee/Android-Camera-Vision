package com.dongkeun.camerawithvision.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dongkeun.camerawithvision.model.DetectedFeature
import com.dongkeun.camerawithvision.model.Label
import com.dongkeun.camerawithvision.model.SendingArgument
import com.google.gson.Gson
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.ByteArrayOutputStream

class CameraViewModel : ViewModel() {
    private val _errorOccurred = MutableLiveData<Boolean>()
    val errorOccurred: LiveData<Boolean> get() = _errorOccurred
    var photo: Bitmap? = null
    var bytesOfPhoto: ByteArray? = null
    var key: String? = null
    private val _labels = MutableLiveData<MutableList<Label>>()
    val labels: LiveData<MutableList<Label>> get() = _labels
    val labelPageVisible = MutableLiveData<Boolean>()
    val labelProgressBarVisible = MutableLiveData<Boolean>()
    private var meaninglessCnt = 0 // Click on Label page

    private val gson = Gson()
    private var job: Job? = null

    init {
        _errorOccurred.value = false
        _labels.value = mutableListOf()
        labelPageVisible.value = false
        meaninglessCnt = 0
        labelPageVisible.value = false
    }

    private fun setLabelPageVisible() {
        labelPageVisible.value = true
        labelProgressBarVisible.value = true
    }

    fun closeLabelPage() {
        _labels.value = mutableListOf()
        labelPageVisible.value = false
    }

    fun clickOnLabelPage() {
        meaninglessCnt++
    }

    fun getBitmap(image: ImageProxy) {
        val buffer = image.planes[0].buffer
        buffer.rewind()
        val bytes = ByteArray(buffer.capacity())
        buffer.get(bytes)
        val clonedBytes = bytes.clone()

        photo = BitmapFactory.decodeByteArray(clonedBytes, 0, clonedBytes.size)
        bytesOfPhoto = imageToBytes(photo!!)
    }

    private fun imageToBytes(photo: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    private fun sendPhotoToWAS() {
        job = CoroutineScope(Job() + Dispatchers.IO)
            .launch {
                val labelSet = sendPhotoToExtractLabels()
                if (labelSet == null) {
                    Timber.i("Network error occurred.")
                    return@launch
                }
                if (labelSet.labels.isNullOrEmpty()) {
                    Timber.i("No label has detected")
                    return@launch
                }

                val labelList = mutableListOf<Label>()
                labelSet.labels.forEach { labelList.add(Label(it)) }
                _labels.postValue(labelList)
                withContext(Dispatchers.Main) { labelProgressBarVisible.value = false }
            }
    }

    private suspend fun sendPhotoToExtractLabels(): DetectedFeature? {
        val url = "../GetLabels" // TODO : Firebase 로 가능한지 알아보기
        val argument = SendingArgument(bytesOfPhoto!!).toJson()
        Timber.i("url: $url")
        Timber.i("argument: ${argument.toString()}")

        val result: DetectedFeature? = null
        // TODO : process

        return result
    }
}