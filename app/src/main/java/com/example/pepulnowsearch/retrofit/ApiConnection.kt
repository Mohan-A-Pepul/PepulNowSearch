package com.example.pepulnowsearch.retrofit

import com.example.pepulnowsearch.data.responsemodel.SearchDataClass
import com.example.pepulnowsearch.data.searchmodel.Data
import com.example.pepulnowsearch.data.searchmodel.Search
import com.example.pepulnowsearch.data.searchmodel.SearchCreatorDataClass
import com.example.pepulnowsearch.utils.constants.Constants
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ApiConnection {
    @POST(Constants.END_NAME_SEARCH)
    suspend fun getApiCreatorSearch(@HeaderMap headerMap: HashMap<String,String>,@Body jsonObject: JsonObject): Response<SearchCreatorDataClass>

    @POST(Constants.END_NAME)
    suspend fun getApiCreatorsSearch():Response<SearchDataClass>
}