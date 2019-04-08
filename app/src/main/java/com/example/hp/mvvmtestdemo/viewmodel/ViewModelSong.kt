package com.example.hp.mvvmtestdemo.viewmodel

import android.databinding.BaseObservable
import com.example.hp.mvvmtestdemo.model.song.Song

class ViewModelSong(song: Song) : BaseObservable() {
    var song = song

}