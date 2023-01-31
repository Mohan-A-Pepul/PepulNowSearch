package com.example.pepulnowsearch.main.ui.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pepulnowsearch.R
import com.example.pepulnowsearch.data.responsemodel.SearchDataClassItem
import com.example.pepulnowsearch.databinding.FragmentFilterSearchCreatorBinding
import com.example.pepulnowsearch.main.ui.adapter.SearchContentAdapter
import com.example.pepulnowsearch.main.ui.viewmodel.SearchCreatorViewModel
import com.example.pepulnowsearch.utils.constants.Constants
import com.example.pepulnowsearch.utils.networkclass.NetworkClass
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FilterSearchCreatorFragment : Fragment() {
    private lateinit var binding: FragmentFilterSearchCreatorBinding
    private val searchRecycleAdapter: SearchContentAdapter by lazy { SearchContentAdapter() }
    private var searchArrayList: ArrayList<SearchDataClassItem> = arrayListOf()
    private val searchLinearLayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    private val searchViewModel: SearchCreatorViewModel by viewModels<SearchCreatorViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterSearchCreatorBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterFunctionSearch("")
        setRecycleAdapter()
        filterUserItemFocus()
        editTextNextKeyboardListen()
        itemSearchListenerFilter()
        responseCategorySearch()

        binding.ivLeftArrowFilter.setOnClickListener {
            findNavController().navigate(R.id.action_filterSearchCreatorFragment_to_searchCreatorUserFragment2)
        }
    }

    private fun itemSearchListenerFilter() {
        binding.evSearchNameFilter.addTextChangedListener(object : TextWatcher {
            var countDownTimer: CountDownTimer? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                countDownTimer?.cancel()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvClearFilter.visibility = View.VISIBLE
                countDownTimer = object : CountDownTimer(500, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        filterFunctionSearch(s)
                    }
                }.start()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun editTextNextKeyboardListen() {
        binding.evSearchNameFilter.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                findNavController().navigate(R.id.action_filterSearchCreatorFragment_to_searchCreatorUserFragment2)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun filterFunctionSearch(s: CharSequence?) {
        val tempfof = ArrayList<SearchDataClassItem>()
        tempfof.clear()
        if (s.toString().trim().isEmpty()) {
            binding.tvClearFilter.visibility = View.GONE
        } else {
            for (item in searchArrayList) {
                if (s != null) {
                    if (item.userName.lowercase()
                            .contains(s.toString().trim().lowercase())
                    ) {
                        tempfof.add(item)
                    }
                }
            }
        }

        searchRecycleAdapter.deleteHistorySearchData { searchDataClassItem, history_position ->
            searchArrayList.remove(searchDataClassItem)
            tempfof.remove(searchDataClassItem)
            searchRecycleAdapter.notifyItemRemoved(history_position)
        }

        if (tempfof.isEmpty() && s.toString().isEmpty()) {
            searchRecycleAdapter.differ.submitList(searchArrayList)
        } else if (tempfof.isEmpty()) {
            searchRecycleAdapter.differ.submitList(tempfof)
        } else {
            searchRecycleAdapter.differ.submitList(tempfof)
        }
        searchRecycleAdapter.notifyDataSetChanged()
    }

    private fun filterUserItemFocus() {
        binding.tvClearFilter.setOnClickListener {
            binding.evSearchNameFilter.setText("")
        }
        binding.evSearchNameFilter.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.tvClearFilter.visibility = View.VISIBLE
            } else binding.tvClearFilter.visibility = View.GONE
        }
    }

    private fun setRecycleAdapter() {
        binding.rvSearchFactorRecycle.adapter = searchRecycleAdapter
        binding.rvSearchFactorRecycle.layoutManager = searchLinearLayoutManager
    }

    private fun responseCategorySearch() {
        searchViewModel.getApiResponseSearch()
        lifecycleScope.launch {
            searchViewModel.searchCategoryResponse.collect() {
                when (it) {
                    is NetworkClass.Loading -> {
                        binding.progressbarFilter.visibility = View.VISIBLE
                    }
                    is NetworkClass.Success -> {
                        Log.d("getApiResponseSearch", it.item.toString())
                        binding.progressbarFilter.visibility = View.GONE
                        searchArrayList=it.item
                        searchRecycleAdapter.differ.submitList(searchArrayList)
                    }
                    is NetworkClass.Error -> {
                        binding.progressbarFilter.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}