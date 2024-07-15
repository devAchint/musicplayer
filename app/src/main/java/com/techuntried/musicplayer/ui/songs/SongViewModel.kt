package com.techuntried.musicplayer.ui.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techuntried.musicplayer.data.models.SongModel
import com.techuntried.musicplayer.data.repository.DataStoreRepository
import com.techuntried.musicplayer.data.repository.RoomRepository
import com.techuntried.musicplayer.data.repository.SongsRepository
import com.techuntried.musicplayer.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val songsRepository: SongsRepository,
    private val roomRepository: RoomRepository,
    private val dataStoreRepository: DataStoreRepository
) :
    ViewModel() {

    private val _songs = MutableStateFlow<Response<List<SongModel>>>(Response.Loading())
    val songs: StateFlow<Response<List<SongModel>>>
        get() = _songs


    init {

        fetchMusicFiles()
    }

    private fun fetchMusicFiles() {
        viewModelScope.launch {
            try {
                val isFirstTime = dataStoreRepository.isFirstTime() ?: true
                if (isFirstTime) {
                    songsRepository.fetchMusicFiles()
                    dataStoreRepository.saveFirstTime(false)
                }
                roomRepository.getAllSongs().collect {
                    _songs.value = Response.Success(it)
                }
            } catch (e: Exception) {
                _songs.value = Response.Error(e.message ?: "An error occurred")
            }
        }
    }

}

