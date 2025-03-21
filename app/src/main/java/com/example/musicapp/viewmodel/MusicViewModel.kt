package com.example.musicapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.data.model.Song
import com.example.musicapp.domain.usecase.NextSongUseCase
import com.example.musicapp.domain.usecase.PreviousSongUseCase
import com.example.musicapp.domain.usecase.SearchSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val searchSongsUseCase: SearchSongUseCase,
    private val nextSongUseCase: NextSongUseCase,
    private val previousSongUseCase: PreviousSongUseCase,
    private val context: Context
) : ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs = _songs.asStateFlow()

    private val _currentSong = MutableStateFlow<Song?>(null)
    val currentSong = _currentSong.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _songProgress = MutableStateFlow(0f)
    val songProgress = _songProgress.asStateFlow()

    private val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _isPlaying.value = isPlaying
                }
            })
        }
    }

    init {
        viewModelScope.launch {
            while (true) {
                _songProgress.value =
                    if (exoPlayer.duration > 0) exoPlayer.currentPosition.toFloat() / exoPlayer.duration else 0f
                delay(500)
            }
        }
    }

    fun searchSongs(query: String) {
        viewModelScope.launch {
            _songs.value = searchSongsUseCase.execute(query)
        }
    }

    fun playOrPauseSong(song: Song) {
        if (_currentSong.value == song && _isPlaying.value) {
            exoPlayer.pause()
        } else {
            _currentSong.value = song
            val mediaItem = MediaItem.fromUri(song.previewUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    fun seekTo(position: Float) {
        val seekPosition = (position * exoPlayer.duration).toLong()
        exoPlayer.seekTo(seekPosition)
    }

    fun nextSong() {
        _currentSong.value?.let {
            _currentSong.value = nextSongUseCase.execute(it, _songs.value)
            _currentSong.value?.let { song -> playOrPauseSong(song) }
        }
    }

    fun previousSong() {
        _currentSong.value?.let {
            _currentSong.value = previousSongUseCase.execute(it, _songs.value)
            _currentSong.value?.let { song -> playOrPauseSong(song) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}


