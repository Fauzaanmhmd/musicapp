package com.example.musicapp.data

import com.example.musicapp.data.model.ApiResponse
import com.example.musicapp.data.remote.MusicApi
import javax.inject.Inject

class MusicRepository @Inject constructor(private val api: MusicApi) {

    suspend fun searchSongs(query: String): ApiResponse {
        return api.searchSongs(query)
    }
}