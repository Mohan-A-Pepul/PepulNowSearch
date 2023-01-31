package com.example.pepulnowsearch.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pepulnowsearch.data.searchmodel.Video
import com.example.pepulnowsearch.databinding.VideoCardBackLayBinding

class VideoContentAdapter : RecyclerView.Adapter<VideoContentAdapter.ViewHolderVideo>() {
    inner class ViewHolderVideo(val binding: VideoCardBackLayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Video) {
            Glide.with(itemView.context).load(data.filearray[0].thumbname)
                .apply(RequestOptions.centerCropTransform()).into(binding.ivVideoImage)
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.userid == newItem.userid
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideo {
        return ViewHolderVideo(
            binding = VideoCardBackLayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
