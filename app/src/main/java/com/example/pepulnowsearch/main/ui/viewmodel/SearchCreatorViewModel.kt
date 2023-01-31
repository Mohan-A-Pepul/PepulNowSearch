package com.example.pepulnowsearch.main.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pepulnowsearch.data.responsemodel.SearchDataClassItem
import com.example.pepulnowsearch.data.searchmodel.Data
import com.example.pepulnowsearch.retrofit.ApiRepository
import com.example.pepulnowsearch.utils.networkclass.NetworkClass
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCreatorViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _searchCategoryResponse: MutableStateFlow<NetworkClass<ArrayList<SearchDataClassItem>>> =
        MutableStateFlow(NetworkClass.Empty)
    val searchCategoryResponse: StateFlow<NetworkClass<ArrayList<SearchDataClassItem>>> =
        _searchCategoryResponse

    private val _searchTabSelectResponse:  MutableStateFlow<NetworkClass<List<Data>>> =
        MutableStateFlow(NetworkClass.Empty)
    val searchTabSelectResponse:StateFlow<NetworkClass<List<Data>>> =
        _searchTabSelectResponse


    fun getApiResponseSearch() {
        _searchCategoryResponse.value = NetworkClass.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val responseCreator = try {
                apiRepository.getApiCreatorSearch()
            } catch (e: Exception) {
                _searchCategoryResponse.value = NetworkClass.Error(e.toString())
                return@launch
            }
            if (responseCreator.isSuccessful && responseCreator.body() != null) {
                responseCreator.body()?.let {
                    _searchCategoryResponse.value = NetworkClass.Success(it)
                    Log.d("getApiResponseSearch", it.toString())
                }
            } else {
                _searchCategoryResponse.value = NetworkClass.Error("No Response Found")
            }
        }
    }


    fun searchResponseSearchTab(jsonObject: JsonObject) {
        _searchTabSelectResponse.value = NetworkClass.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val responseCreators = try {
                apiRepository.getApiCreator(jsonObject)
            } catch (e: Exception) {
                Log.d("getApiResponseSearchTab", e.toString())
                _searchTabSelectResponse.value = NetworkClass.Error(e.toString())
                return@launch
            }
            if (responseCreators.isSuccessful && responseCreators.body() != null) {
                responseCreators.body()?.let {
                    _searchTabSelectResponse.value = NetworkClass.Success(it.data)
                    Log.d("getApiResponseSearchTab", it.data.toString())
                }
            } else {
                Log.d("getApiResponseSearchTab", "Error")
                _searchTabSelectResponse.value = NetworkClass.Error("No Response Found")
            }
        }
    }
}