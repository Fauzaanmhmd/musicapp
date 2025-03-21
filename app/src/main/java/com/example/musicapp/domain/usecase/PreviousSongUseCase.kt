package com.example.musicapp.domain.usecase

import com.example.musicapp.data.model.Song
import javax.inject.Inject

class PreviousSongUseCase @Inject constructor() {
    fun execute(currentSong: Song, songList: List<Song>): Song? {
        val currentIndex = songList.indexOf(currentSong)
        return if (currentIndex > 0) {
            songList[currentIndex - 1]
        } else null
    }
}