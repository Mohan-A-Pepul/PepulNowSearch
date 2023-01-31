package com.example.pepulnowsearch.main.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pepulnowsearch.R
import com.example.pepulnowsearch.data.responsemodel.SearchDataClassItem
import com.example.pepulnowsearch.databinding.SearchlayouthistoryBinding
import com.example.pepulnowsearch.utils.enum_class.SearchSuggestionUserTypeEnum

class SearchContentAdapter : RecyclerView.Adapter<SearchContentAdapter.ViewHolderSearchOne>() {
    private lateinit var passPosDeleteHistory:((SearchDataClassItem,Int)->Unit)
    inner class ViewHolderSearchOne(val binding: SearchlayouthistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItemsSearch(data: SearchDataClassItem) {
            binding.tvHistoryUserName.text = data.userName
            binding.ivCloseDelete.setOnClickListener {
                passPosDeleteHistory.invoke(data,adapterPosition)
            }
            when (data.type) {
                SearchSuggestionUserTypeEnum.USER.creatorType -> {

                    Glide.with(itemView.context).load(data.userImage)
                        .apply(RequestOptions.circleCropTransform()).into(binding.ivHistoryIcon)
                }
                SearchSuggestionUserTypeEnum.CREATOR.creatorType  -> {
                    Log.d("filterFunctionSearchss", data.type)
                    Glide.with(itemView.context)
                        .load(data.userImage).apply(
                            RequestOptions.circleCropTransform()
                        ).into(binding.ivHistoryIcon)
                }
                else -> {
                    Glide.with(itemView.context)
                        .load(R.drawable.ic_baseline_search_24).apply(
                            RequestOptions.circleCropTransform()
                        ).into(binding.ivHistoryIcon)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<SearchDataClassItem>() {
        override fun areItemsTheSame(
            oldItem: SearchDataClassItem,
            newItem: SearchDataClassItem
        ): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(
            oldItem: SearchDataClassItem,
            newItem: SearchDataClassItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearchOne {
        return ViewHolderSearchOne(
            binding = SearchlayouthistoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderSearchOne, position: Int) {
        holder.bindItemsSearch(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun deleteHistorySearchData(listener:((SearchDataClassItem,Int)->Unit)){
        passPosDeleteHistory=listener
    }
}