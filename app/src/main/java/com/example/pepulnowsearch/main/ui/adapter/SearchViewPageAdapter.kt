package com.example.pepulnowsearch.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pepulnowsearch.main.ui.view.fragment.CreatorsFragment
import com.example.pepulnowsearch.main.ui.view.fragment.PeopleFragment
import com.example.pepulnowsearch.main.ui.view.fragment.PhotoStoriesFragment
import com.example.pepulnowsearch.main.ui.view.fragment.VideoStoriesFragment

class SearchViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreatorsFragment()
            1 -> PeopleFragment()
            2 -> PhotoStoriesFragment()
            3 -> VideoStoriesFragment()
            else -> {
                CreatorsFragment()
            }
        }
    }
}