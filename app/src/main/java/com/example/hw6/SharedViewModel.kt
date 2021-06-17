package com.example.hw6

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SharedViewModel(val context: Context):ViewModel() {

    private val dao: VideoDatabaseDao = VideoDatabase.getInstance(context).databaseDao
    //ListFragment Data
    private val _videos = MutableLiveData<List<VideoData>>()

    public val videos: LiveData<List<VideoData>>
        get() = _videos

    //Preview Fragment

    private val _selectedVideoData = MutableLiveData<VideoData>()
    public val selectedVideoData: LiveData<VideoData>
        get() = _selectedVideoData

    public var selectedVideo = 0
        set(value){
            _videos.value?.let {
                if(value >= 0 && value< it.size){
                    field = value
                    position = 0
                    _selectedVideoData.value = it.get(value)
                }
            }
        }

    public var position = 0


    public fun reloadList() = viewModelScope.launch(Dispatchers.Main){
        _videos.value = listOf<VideoData>()
        viewModelScope.launch(Dispatchers.IO){
            dao.clear()
            loadList()
        }
    }

    public  fun loadList() = viewModelScope.launch(Dispatchers.Main){
        val list = async(Dispatchers.IO){
            dao.loadAll()
        }.await()

        if(list.size>0){
            _videos.value = list
        }else{
            YoutubeSAX(object : Parser_Listener {
                override fun start() {

                }
                override fun finish(videos: List<VideoData>) {
                    viewModelScope.launch(Dispatchers.IO) {
                        dao.insertList(videos)
                    }
                    _videos.value = videos

                }

            }).parseURL("https://www.youtube.com/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw")
        }


    }

}