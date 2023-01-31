package com.example.pepulnowsearch.data.searchmodel

data class  Data(
    val SearchName: String,
    val search: List<Search>,
    val searchtype: Int,
    val title: List<Title>
)