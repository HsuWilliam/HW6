package com.example.hw6

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragment) as YoutubeListFragment?
//        listFragment?.listener = object: YoutubeListFragment.OnChannelSelectedListener {
//            override fun onChannelSelected(title: String, cover: Bitmap, url: String) {
//                val previewFragment = supportFragmentManager.findFragmentById(R.id.previewFragment) as YoutubePreviewFragment?
//                if (previewFragment != null) {
//                    // send fragment information (0: start position)
//                    previewFragment.previewChannel(title, cover, url, 0)
//                }
//            }
//
//        }
//    }

        //   override fun OnItemClick(position: Int) {
        //      val intent = Intent(this,PreviewActivity::class.java)
        //     intent.putExtra("title",data[position].title)

        // startActivity(intent)

        //}


    }
}
