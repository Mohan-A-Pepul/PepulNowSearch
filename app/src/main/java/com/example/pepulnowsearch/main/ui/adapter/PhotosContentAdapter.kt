package com.example.pepulnowsearch.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pepulnowsearch.data.model.photodataclass.PhotoDataList
import com.example.pepulnowsearch.databinding.PhotosCardLayoutBinding

class PhotosContentAdapter:RecyclerView.Adapter<PhotosContentAdapter.ViewHolderPhotoOne>() {
    inner class ViewHolderPhotoOne(val binding:PhotosCardLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:PhotoDataList){
            binding.tvPhotosName.text=data.photo_user_name
            Glide.with(itemView.context).load(data.photo_post_image).apply(RequestOptions.centerCropTransform()).into(binding.ivPhotosFullImage)
            Glide.with(itemView.context).load(data.photo_user_image).apply(RequestOptions.circleCropTransform()).into(binding.ivPhotosCircle)
        }
    }
    private val differCallBack = object :DiffUtil.ItemCallback<PhotoDataList>(){
        override fun areItemsTheSame(oldItem: PhotoDataList, newItem: PhotoDataList): Boolean {
            return oldItem.photo_user_id==newItem.photo_user_id
        }

        override fun areContentsTheSame(oldItem: PhotoDataList, newItem: PhotoDataList): Boolean {
            return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhotoOne {
        return ViewHolderPhotoOne(binding = PhotosCardLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderPhotoOne, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}