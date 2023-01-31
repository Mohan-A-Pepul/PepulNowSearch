package com.example.pepulnowsearch.data.model.creatorclass

data class CreatorVideoList(
    val creatorId: Int,
    val creatorUserImage: String,
    val creatorVideoImage: String,
    val videoDescription: String,
    val videoPremiumType: Int,
    val creatorTag: String
)
