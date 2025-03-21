package com.example.musicapp.data.remote

import com.example.musicapp.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET("search")
    suspend fun searchSongs(@Query("q") query: String): ApiResponse
}