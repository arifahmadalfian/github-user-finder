package com.arifahmadalfian.githubuserfinder.network.model

import com.google.gson.annotations.SerializedName

data class AllUsers(

	@field:SerializedName("items")
	val items: ArrayList<Users>
)
