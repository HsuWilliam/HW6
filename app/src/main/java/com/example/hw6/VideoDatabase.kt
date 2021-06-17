package com.example.hw6

import android.content.Context
import android.provider.MediaStore
import androidx.room.*

@Database(entities=[VideoData::class],version = 1)
@TypeConverters(Converters::class)

abstract class VideoDatabase: RoomDatabase() {
    abstract val databaseDao: VideoDatabaseDao
    companion object{
        private var INSTANCE: VideoDatabase? = null
        fun getInstance(context: Context):VideoDatabase{
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext,VideoDatabase::class.java,"Videos.db").build()
            }
            INSTANCE = instance
            return instance
        }
    }

}