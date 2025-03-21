package com.example.musicapp.domain.usecase

import com.example.musicapp.data.model.Song

class PreviousSongUseCase {
    fun execute(currentSong: Song, songs: List<Song>): Song? {
        val currentIndex = songs.indexOf(currentSong)
        return if (currentIndex > 0) {
            songs[currentIndex - 1]
        } else {
            songs.lastOrNull()
        }
    }
}