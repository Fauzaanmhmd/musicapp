package com.example.musicapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicapp.presentation.components.SongItem
import com.example.musicapp.viewmodel.MusicViewModel
import com.example.musicapp.data.model.Song
import com.example.musicapp.presentation.components.MusicControls

@Composable
fun MusicScreen(viewModel: MusicViewModel = hiltViewModel()) {
    var query by remember { mutableStateOf("") }
    val songs by viewModel.songs.collectAsState()
    val currentSong by viewModel.currentSong.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Cari Lagu...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = { viewModel.searchSongs(query) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(songs) { song ->
                SongItem(song = song, onClick = { viewModel.playOrPauseSong(song) }, isPlaying = song == currentSong && isPlaying)
            }
        }
        currentSong?.let {
            NowPlayingSection(song = it, viewModel)
            MusicControls(
                isPlaying = isPlaying,
                onPrevious = { viewModel.previousSong() },
                onPlayPause = { viewModel.playOrPauseSong(it) },
                onNext = { viewModel.nextSong() }
            )
        }
    }
}

@Composable
fun NowPlayingSection(song: Song, viewModel: MusicViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Now Playing: ${song.title}", style = MaterialTheme.typography.titleLarge)
    }
}
