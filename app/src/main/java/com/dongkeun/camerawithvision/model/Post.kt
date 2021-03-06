package com.dongkeun.camerawithvision.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class Post(
    @field:SerializedName("UserId")
    val userId: String,
    @field:SerializedName("PostId")
    val postId: String,
    @field:SerializedName("PostedTime")
    val postedTime: String,
    @field:SerializedName("PhotoBytes")
    val photoBytes: String,
    @field:SerializedName("Content")
    val content: String,
    @field:SerializedName("LikedCount")
    val likedCount: Int,
    @field:SerializedName("Comment")
    val comment: Comment,
    @field:SerializedName("IsDeleted")
    val isDeleted: Int,

    var bitmap: Bitmap
)