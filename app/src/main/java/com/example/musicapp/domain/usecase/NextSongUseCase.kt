package com.example.musicapp.domain.usecase

import com.example.musicapp.data.model.Song

class NextSongUseCase {
    fun execute(currentSong: Song, songs: List<Song>): Song? {
        val currentIndex = songs.indexOf(currentSong)
        return if (currentIndex != -1 && currentIndex < songs.size - 1) {
            songs[currentIndex + 1]
        } else {
            songs.firstOrNull()
        }
    }
}