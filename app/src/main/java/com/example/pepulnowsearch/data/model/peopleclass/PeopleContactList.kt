package com.example.pepulnowsearch.data.model.peopleclass

data class PeopleContactList(
    val peopleId: Int,
    val peopleUserName: String,
    val privateAccount:Int,
    val peopleUserMutualFriends: String,
    var followStatusPeopleType: Int,
    val peopleUserProfile: String
)
