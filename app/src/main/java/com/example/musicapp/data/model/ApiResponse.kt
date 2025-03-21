package com.example.musicapp.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data") val songs: List<Song>
)