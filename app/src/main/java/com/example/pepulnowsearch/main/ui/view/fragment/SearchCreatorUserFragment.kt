package com.example.pepulnowsearch.main.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pepulnowsearch.R
import com.example.pepulnowsearch.databinding.CreatorDynamicBadgeSelectedBinding
import com.example.pepulnowsearch.databinding.FragmentSearchCreatorUserBinding
import com.example.pepulnowsearch.main.ui.adapter.SearchViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCreatorUserFragment : Fragment() {
    private lateinit var binding: FragmentSearchCreatorUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchCreatorUserBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabAdapter()
        searchItemFocus()
    }

    private fun searchItemFocus() {
        binding.evSearchNameFilter.setOnClickListener {
            findNavController().navigate(R.id.action_searchCreatorUserFragment2_to_filterSearchCreatorFragment)
        }
    }

    private fun setTabAdapter() {
        val tabLayout = binding.tabCreatorList
        val viewPageScreen = binding.vpFrameViewPage
        val tabLaySelected = layoutInflater.inflate(R.layout.creator_dynamic_badge_selected, null)
        val tabLayUnSelected = layoutInflater.inflate(R.layout.creator_dynamic_badge_unselect, null)
        viewPageScreen.adapter = SearchViewPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val imageLogoSelect = tabLaySelected.findViewById<ImageView>(R.id.badge_logo_select)
        val imageLogoUnselect = tabLayUnSelected.findViewById<ImageView>(R.id.badge_logo_unselect)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0 && tab.isSelected) {
                    tab.customView = tabLaySelected
                    Glide.with(requireContext()).load(R.drawable.logos_badge)
                        .apply(RequestOptions.circleCropTransform()).into(imageLogoSelect)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab?.position == 0 && !tab.isSelected) {
                    tab.customView = tabLayUnSelected
                    Glide.with(requireContext()).load(R.drawable.logos_badge)
                        .apply(RequestOptions.circleCropTransform()).into(imageLogoUnselect)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab?.position == 0 && tab.isSelected) {
                    tab.customView = tabLaySelected
                    Glide.with(requireContext()).load(R.drawable.logos_badge)
                        .apply(RequestOptions.circleCropTransform()).into(imageLogoSelect)
                }
            }

        })

        TabLayoutMediator(tabLayout, viewPageScreen) { tabView, position ->
            when (position) {
                0 -> {
                }
                1 -> {
                    tabView.text = "People"
                }
                2 -> {
                    tabView.text = "Photo Stories"
                }
                3 -> {
                    tabView.text = "Video Stories"
                }
            }
        }.attach()
    }
}
