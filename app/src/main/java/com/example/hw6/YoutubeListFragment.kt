package com.example.hw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.ListFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.databinding.FragmentYoutubeListBinding

class YoutubeListFragment : ListFragment(),cnnlistAdapter.OnItemClickListener {

    private lateinit var model:SharedViewModel



    var data = mutableListOf<VideoData>()
    //  val adapter : ArrayAdapter<String> by lazy{
    //    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles)
    // }
    //val VideoList : List<VideoData> = listOf<VideoData>()
    // val adapter: VideoAdapter by lazy {
    //     VideoAdapter(this)
    //  }
//    interface OnChannelSelectedListener {
//        fun onChannelSelected(title: String, image: Bitmap, videoId: String)
//    }
//    var listener: OnChannelSelectedListener? = null
    val adapter: cnnlistAdapter by lazy {
        cnnlistAdapter(requireContext(), this)
    }
    

    lateinit var binding: FragmentYoutubeListBinding

    val swipeRefreshLayout by lazy{
        binding.swipeRefreshLayout
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        model = ViewModelProvider(requireActivity(),SharedViewModelFactory(requireContext())).get(SharedViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_youtube_list,container,false)

        val mrecyclerView by lazy{
            binding.recyclerView
        }
        mrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mrecyclerView.adapter = adapter

        model.videos.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            adapter.Video = it
        })

        swipeRefreshLayout.setOnClickListener{
            swipeRefreshLayout.isRefreshing = true
            model.reloadList()
        }

        swipeRefreshLayout.isRefreshing = true
        model.loadList()



        return binding.root




    }
    //   override fun OnItemClick(position: Int) {
    //      val intent = Intent(this,PreviewActivity::class.java)
    //     intent.putExtra("title",data[position].title)

    // startActivity(intent)

    //}


    override fun onItemClick(item: VideoData, position: Int) {
        model.selectedVideo = position

        if(parentFragment != null){   //small display
            var dir = YoutubeListFragmentDirections.actionYoutubeListFragmentToYoutubePreviewFragment()
            //var dir = YoutubeListFragmentDirections.actionYoutubeListFragmentToYoutubePreviewFragment()
            findNavController().navigate(dir)
        }

    }


}

