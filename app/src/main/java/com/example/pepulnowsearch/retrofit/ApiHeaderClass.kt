package com.example.pepulnowsearch.retrofit

import com.example.pepulnowsearch.BuildConfig
import com.example.pepulnowsearch.utils.constants.Constants

class ApiHeaderClass : HashMap<String, String>() {
    fun getApiHeader(): HashMap<String, String> {
        val header = HashMap<String, String>()
        header["Authorization"] = Constants.USER_TOKEN
        header["Device-Token"] = Constants.DEVICE_TOKEN
        header["Platform"] = "ANDROID"
        header["App-Version"] = BuildConfig.VERSION_NAME
        return header
    }
}
