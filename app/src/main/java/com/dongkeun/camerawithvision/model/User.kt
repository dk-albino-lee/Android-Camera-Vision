package com.dongkeun.camerawithvision.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("UserId")
    val userId: String,
    @field:SerializedName("EncryptedPassword")
    val password: String,
    @field:SerializedName("PostList")
    val postList: MutableList<Post>,
    @field:SerializedName("LikedList")
    val likedPostList: MutableList<Post>,
    @field:SerializedName("Thumbnail")
    val thumbnailStr: String,
    @field:SerializedName("SelfIntro")
    val selfIntro: String
)
