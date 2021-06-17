package com.example.hw6

interface Parser_Listener {
    fun start()

    fun finish(video: List<VideoData>) {
    }
}