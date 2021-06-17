package com.example.hw6

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos_table")


class VideoData(var title:String="", val thumbnail: Bitmap? = null, val description:String="", val videoId:String="") {
    @PrimaryKey(autoGenerate = true)

    var id :Int = 0


}