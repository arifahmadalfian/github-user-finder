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