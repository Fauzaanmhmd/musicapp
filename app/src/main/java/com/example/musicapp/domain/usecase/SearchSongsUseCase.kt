package com.example.musicapp.domain.usecase

import com.example.musicapp.data.MusicRepository
import com.example.musicapp.data.model.Song
import javax.inject.Inject

class SearchSongUseCase @Inject constructor(private val repository: MusicRepository) {
    suspend fun execute(query: String): List<Song> {
        return repository.searchSongs(query).songs
    }
}
