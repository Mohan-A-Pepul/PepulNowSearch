package com.example.pepulnowsearch.main.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pepulnowsearch.R
import com.example.pepulnowsearch.data.model.photodataclass.PhotoDataList
import com.example.pepulnowsearch.databinding.FragmentPhotoStoriesBinding
import com.example.pepulnowsearch.main.ui.adapter.PhotosContentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoStoriesFragment : Fragment() {
    private lateinit var binding: FragmentPhotoStoriesBinding
    private val photoArrayList:ArrayList<PhotoDataList> by lazy { ArrayList() }
    private val photoAdapter:PhotosContentAdapter by lazy { PhotosContentAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentPhotoStoriesBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getPhotoStoriesData()
    }

    private fun getPhotoStoriesData() {
        photoArrayList.add(PhotoDataList(1,"https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/advice/maps-satellite-images/satellite-image-of-globe.jpg"
        ,"https://imgv3.fotor.com/images/blog-cover-image/part-blurry-image.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(2,"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(3,"https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/advice/maps-satellite-images/satellite-image-of-globe.jpg"
            ,"https://imgv3.fotor.com/images/blog-cover-image/part-blurry-image.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(4,"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(5,"https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/advice/maps-satellite-images/satellite-image-of-globe.jpg"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(6,"https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/advice/maps-satellite-images/satellite-image-of-globe.jpg"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(7,"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(8,"https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/advice/maps-satellite-images/satellite-image-of-globe.jpg"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(9,"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
            ,"https://imgv3.fotor.com/images/blog-cover-image/part-blurry-image.jpg","JahalKabir"))
        photoArrayList.add(PhotoDataList(10,"https://www.metoffice.gov.uk/binaries/content/gallery/metofficegovuk/hero-images/advice/maps-satellite-images/satellite-image-of-globe.jpg"
            ,"https://images.ctfassets.net/hrltx12pl8hq/3j5RylRv1ZdswxcBaMi0y7/b84fa97296bd2350db6ea194c0dce7db/Music_Icon.jpg","JahalKabir"))
    }

    private fun setAdapter() {
        binding.rvPhotosRecycle.layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        binding.rvPhotosRecycle.adapter=photoAdapter
        photoAdapter.differ.submitList(photoArrayList)
    }
}