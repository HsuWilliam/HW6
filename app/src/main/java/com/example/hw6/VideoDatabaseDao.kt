package com.example.hw6

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface VideoDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<VideoData>)

    @Query("SELECT * FROM videos_table")
    fun loadAll(): List<VideoData>

    @Query("DELETE FROM videos_table")
    fun clear()

    
}