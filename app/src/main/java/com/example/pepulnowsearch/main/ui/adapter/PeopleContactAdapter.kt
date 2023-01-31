package com.example.pepulnowsearch.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pepulnowsearch.R
import com.example.pepulnowsearch.data.model.peopleclass.PeopleContactList
import com.example.pepulnowsearch.databinding.PeopleListLayoutBinding
import com.example.pepulnowsearch.utils.enum_class.FollowStatusEnumClass

class PeopleContactAdapter : RecyclerView.Adapter<PeopleContactAdapter.ViewHolderOnePeople>() {
    private lateinit var passButtonStateData:((PeopleContactList,Int)->Unit)
    inner class ViewHolderOnePeople(val binding: PeopleListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(data: PeopleContactList) {
            Glide.with(itemView.context).load(data.peopleUserProfile).apply(RequestOptions.circleCropTransform())
                .into(binding.ivPeopleDetailsIcon)
            binding.tvPeopleMutualFriends.text = data.peopleUserMutualFriends
            binding.tvPeopleUserName.text = data.peopleUserName
            binding.btnLinearFollow.setOnClickListener {
                passButtonStateData.invoke(data,adapterPosition)
            }
            when(data.followStatusPeopleType){
                FollowStatusEnumClass.FOLLOW.ordinal -> {
                    binding.btnLinearFollow.setBackgroundResource(R.drawable.follow_button)
                    binding.ivNotifyIcon.setImageResource(R.drawable.ic_baseline_add_24)
                    binding.tvFollowMessage.text= itemView.context.getString(R.string.Follow)
                    binding.tvFollowMessage.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
                }
                FollowStatusEnumClass.UNFOLLOW.ordinal -> {
                    binding.btnLinearFollow.setBackgroundResource(R.drawable.following_button)
                    binding.ivNotifyIcon.setImageResource(R.drawable.follow_icon_user_tick)
                    binding.tvFollowMessage.text=itemView.context.getString(R.string.Following)
                    binding.tvFollowMessage.setTextColor(ContextCompat.getColor(itemView.context,R.color.mediumblue))
                }
                FollowStatusEnumClass.REQUEST.ordinal -> {
                    binding.btnLinearFollow.setBackgroundResource(R.drawable.request_button)
                    binding.ivNotifyIcon.setImageResource(R.drawable.ic_notification)
                    binding.tvFollowMessage.text=itemView.context.getString(R.string.Requested)
                    binding.tvFollowMessage.setTextColor(ContextCompat.getColor(itemView.context,R.color.light_green_new))
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<PeopleContactList>() {
        override fun areItemsTheSame(
            oldItem: PeopleContactList,
            newItem: PeopleContactList
        ): Boolean {
            return oldItem.peopleId == newItem.peopleId
        }

        override fun areContentsTheSame(
            oldItem: PeopleContactList,
            newItem: PeopleContactList
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ=AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderOnePeople {
        return ViewHolderOnePeople(binding = PeopleListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderOnePeople, position: Int) {
       holder.bindItems(differ.currentList[position])
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
    fun dataPassForButtonStateChange(listener: ((PeopleContactList,Int)->Unit)){
        passButtonStateData=listener
    }
}