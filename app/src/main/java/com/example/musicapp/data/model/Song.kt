package com.example.musicapp.data.model

import com.google.gson.annotations.SerializedName

data class Song(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("preview") val previewUrl: String,
    @SerializedName("artist") val artist: Artist,
    @SerializedName("album") val album: Album
)