package com.techuntried.musicplayer.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo("songName")
    val songName: String,
    @ColumnInfo("artist")
    val artist: String,
    @ColumnInfo("album")
    val album:String,
    @ColumnInfo("uri")
    val uri: String
)