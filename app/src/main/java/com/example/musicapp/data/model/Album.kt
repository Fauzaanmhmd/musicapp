package com.example.musicapp.data.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("cover") val coverUrl: String
)