package com.example.pepulnowsearch.main.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pepulnowsearch.data.searchmodel.Video
import com.example.pepulnowsearch.databinding.FragmentVideoStoriesBinding
import com.example.pepulnowsearch.main.ui.adapter.VideoContentAdapter
import com.example.pepulnowsearch.main.ui.viewmodel.SearchCreatorViewModel
import com.example.pepulnowsearch.utils.constants.Constants
import com.example.pepulnowsearch.utils.networkclass.NetworkClass
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoStoriesFragment : Fragment() {
    private lateinit var binding: FragmentVideoStoriesBinding
    private val videoContentAdapter: VideoContentAdapter by lazy { VideoContentAdapter() }
    private var videoArrayList: List<Video> = listOf()
    private val searchViewModel: SearchCreatorViewModel by viewModels<SearchCreatorViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoStoriesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterVideo()
        getDataForVideoPage()
    }

    private fun getDataForVideoPage() {
        searchViewModel.searchResponseSearchTab(requestJsonResponse())
        Log.d("getApiResponseSearchTab",requestJsonResponse().toString())
        lifecycleScope.launch {
            searchViewModel.searchTabSelectResponse.collect() {
                when (it) {
                    is NetworkClass.Loading -> {

                    }
                    is NetworkClass.Success -> {
                        Log.d("getApiResponsefinal", it.item[0].search[0].videos.toString())
                        videoArrayList =  it.item[0].search[0].videos
                        videoContentAdapter.differ.submitList(videoArrayList)
                    }
                    is NetworkClass.Error -> {

                    }
                }
            }
        }
    }
    private fun requestJsonResponse(): JsonObject {
        val jsonObject = JsonObject()
        try {
            jsonObject.addProperty("userId", Constants.USER_ID)
            jsonObject.addProperty("searchname", "nature")
            jsonObject.addProperty("searchcontent", "videos")
            jsonObject.addProperty("usertoken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMjI4NzMzNjMifQ.w7arJHvrGcsRgG5AGi2-eYDnkpe2FPuRU1dfd1203Bg")
            jsonObject.addProperty("deviceToken", "fil5JmAmRgWtZx9OlWQyWb:APA91bGzl9pvsSE0vFlRmPLJSHXvvvekXwUWOoZiftPHisRtq7_scmG_Cb-X17Vh3b0mb5wZWihpR7lofeHTBlezqeCgEMb4DL4NCggGRLRfdUVJdwXFXvaAroX-LBeh630UXc3uS5A-")
            Log.e("response", jsonObject.toString())
        } catch (e: Exception) {
            Log.e("response", e.toString())
        }
        return jsonObject
    }

    private fun setAdapterVideo() {
        binding.rvVideoRecycle.adapter = videoContentAdapter
        binding.rvVideoRecycle.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }
}