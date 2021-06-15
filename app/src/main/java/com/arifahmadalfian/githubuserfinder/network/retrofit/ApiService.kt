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
