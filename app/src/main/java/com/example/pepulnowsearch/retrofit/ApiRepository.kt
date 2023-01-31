package com.example.pepulnowsearch.retrofit

import com.google.gson.JsonObject
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getApiCreator(jsonObject: JsonObject)=apiHelper.getApiCreator(jsonObject)

    suspend fun getApiCreatorSearch()=apiHelper.getApiCreatorSearch()

}