package com.example.hw6

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe

import androidx.navigation.fragment.navArgs
import com.example.hw6.databinding.FragmentYoutubePreviewBinding
import com.google.android.youtube.player.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.fixedRateTimer


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [YoutubePreviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YoutubePreviewFragment : Fragment() {

    private lateinit var model:SharedViewModel

    private lateinit var binding: FragmentYoutubePreviewBinding
    private lateinit var YoutubePlayer: YouTubePlayerSupportFragment

    //lateinit var youtubePlayer: YouTubePlayer
    //private var initializedYouTubePlayer: YouTubePlayer? = null

//    private val args: YoutubePreviewFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(requireActivity(),SharedViewModelFactory(requireContext())).get(SharedViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youtube_preview, container, false)

        try{
            val video = model.selectedVideoData.value
            model.selectedVideoData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                previewChannel(it.title,it.thumbnail!!,it.videoId,model.position)
            })
        }catch (e:Exception){
            e.printStackTrace()
        }

        return binding.root
    }


    fun previewChannel(Title: String, image: Bitmap, VideoId: String, position: Int) {


        binding.title = Title
        binding.thumbnail = image
        binding.videoId = VideoId



        val player = childFragmentManager?.findFragmentById(R.id.youtubeplayerfragment) as YouTubePlayerSupportFragment?
        player?.initialize(getString(R.string.api_key),object:YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
            ) {
                p1?.cueVideo(VideoId)

            }

            override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
            ) {

            }
        } )







        //    var youtubeplayer =  view?.findViewById<YouTubePlayerView>(R.id.youtubeplayerfragment)
        //    youtubeplayer?.initialize(getString(R.string.api_key),object : YouTubePlayer.OnInitializedListener{

//        initPlayer("XfP31eWXli4")
//         initPlayers("XfP31eWXli4")


        //    val youtubeplayers =
        //       view?.findViewById<com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView>(
        //           R.id.youtubeplayer
        //       )

        //   getLifecycle().addObserver(youtubeplayers!!)
        //   youtubeplayers!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        //      fun onReady(youTubePlayer: YouTubePlayer) {
        //          youTubePlayer.cueVideo(videoId, 0)
        //         initializedYouTubePlayer = youTubePlayer
        //     }
        //  }
        //)

    }

}