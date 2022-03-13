package com.dongkeun.camerawithvision.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @field:SerializedName("CommentedTime")
    val commentedTime: String,
    @field:SerializedName("CommentedUser")
    val commentedUser: User,
    @field:SerializedName("Comment")
    val comment: String,
    @field:SerializedName("IsDeleted")
    val isDeleted: Int

)