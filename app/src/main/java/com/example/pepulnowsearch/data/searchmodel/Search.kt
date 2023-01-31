package com.example.pepulnowsearch.data.searchmodel

data class Search(
    val peoples: List<People>,
    val photos: List<Photo>,
    val stories: List<Any>,
    val videos: List<Video>
)