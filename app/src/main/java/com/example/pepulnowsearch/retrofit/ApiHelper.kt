package com.example.pepulnowsearch.retrofit

import com.google.gson.JsonObject
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiConnection: ApiConnection) {

    suspend fun getApiCreator(jsonObject: JsonObject) = apiConnection.getApiCreatorSearch(ApiHeaderClass().getApiHeader(),jsonObject)

    suspend fun getApiCreatorSearch() = apiConnection.getApiCreatorsSearch()
}