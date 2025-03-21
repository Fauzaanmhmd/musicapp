package com.example.musicapp.domain.usecase

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.data.model.Song
import javax.inject.Inject

class PlaySongUseCase @Inject constructor(private val exoPlayer: ExoPlayer) {
    fun execute(song: Song, isPlaying: Boolean): Boolean {
        return if (isPlaying) {
            exoPlayer.pause()
            false
        } else {
            val mediaItem = MediaItem.fromUri(song.previewUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
            true
        }
    }
}
