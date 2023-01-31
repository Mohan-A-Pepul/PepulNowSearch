package com.example.pepulnowsearch.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pepulnowsearch.R
import com.example.pepulnowsearch.data.model.creatorclass.CreatorVideoList
import com.example.pepulnowsearch.databinding.CreatorLayoutBackListBinding
import com.example.pepulnowsearch.utils.enum_class.CreatorVideoPremiumTypeEnum

class CreatorVideoPageAdapter : RecyclerView.Adapter<CreatorVideoPageAdapter.ViewHolderOneCreator>() {
    inner class ViewHolderOneCreator(val binding: CreatorLayoutBackListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(data: CreatorVideoList) {
            Glide.with(itemView.context).load(data.creatorVideoImage).apply(RequestOptions.fitCenterTransform())
                .into(binding.ivVideoImageFront)
            Glide.with(itemView.context).load(data.creatorUserImage).apply(RequestOptions.centerCropTransform())
                .into(binding.ivCreatorUserProfile)
            binding.tvDescriptionContent.text=data.videoDescription
            binding.tvSongsCreatorTag.text=data.creatorTag
            when(data.videoPremiumType) {
                CreatorVideoPremiumTypeEnum.PREMIUM.ordinal -> binding.tvFreePremiumDecide.setBackgroundResource(R.drawable.premium_icon_creators)
                CreatorVideoPremiumTypeEnum.FREE.ordinal  -> {
                    binding.tvFreePremiumDecide.text=itemView.context.getString(R.string.Free)
                    binding.tvFreePremiumDecide.setBackgroundResource(R.drawable.free_icon_creator )
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<CreatorVideoList>() {
        override fun areItemsTheSame(
            oldItem: CreatorVideoList,
            newItem: CreatorVideoList
        ): Boolean {
            return oldItem.creatorId == newItem.creatorId
        }

        override fun areContentsTheSame(
            oldItem: CreatorVideoList,
            newItem: CreatorVideoList
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ=AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderOneCreator {
        return ViewHolderOneCreator(binding = CreatorLayoutBackListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderOneCreator, position: Int) {
        holder.bindItems(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}