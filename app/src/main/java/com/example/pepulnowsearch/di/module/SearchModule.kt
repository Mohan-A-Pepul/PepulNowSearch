package com.example.pepulnowsearch.di.module

import android.util.Log
import com.example.pepulnowsearch.retrofit.ApiConnection
import com.example.pepulnowsearch.utils.constants.Constants
import com.google.gson.JsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {
    @Provides
    fun getBaseUrl():String = Constants.BASE_URL_SEARCH

    @Singleton
    @Provides
    fun getRetrofitBase(BaseUrl: String):ApiConnection =
        Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiConnection::class.java)

    @Singleton
    @Provides
    fun requestJsonResponse():JsonObject {
        val jsonObject = JsonObject()
        try {
            jsonObject.addProperty("userId", Constants.USER_ID)
            jsonObject.addProperty("userToken", Constants.USER_TOKEN)
            jsonObject.addProperty("device_Token", Constants.DEVICE_TOKEN)
            Log.e("response", jsonObject.toString())
        } catch (e: Exception) {
            Log.e("response", e.toString())
        }
        return jsonObject
    }
}