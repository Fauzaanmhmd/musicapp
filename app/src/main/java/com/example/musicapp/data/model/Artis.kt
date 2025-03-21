package com.example.musicapp.data.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val pictureUrl: String
)