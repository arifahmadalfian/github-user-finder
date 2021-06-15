package com.arifahmadalfian.githubuserfinder.network.model

import com.google.gson.annotations.SerializedName

data class Users(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:SerializedName("login")
    val login: String? = null
)