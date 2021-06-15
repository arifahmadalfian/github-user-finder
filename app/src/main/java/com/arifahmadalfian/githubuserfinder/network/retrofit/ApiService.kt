package com.arifahmadalfian.githubuserfinder.network.retrofit

import com.arifahmadalfian.githubuserfinder.network.model.AllUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") name: String
    ): Call<AllUsers>

}