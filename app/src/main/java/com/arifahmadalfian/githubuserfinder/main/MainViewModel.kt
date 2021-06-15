package com.arifahmadalfian.githubuserfinder.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arifahmadalfian.githubuserfinder.network.model.AllUsers
import com.arifahmadalfian.githubuserfinder.network.model.Users
import com.arifahmadalfian.githubuserfinder.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainViewModel: ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _users = MutableLiveData<ArrayList<Users>>()
    val users: LiveData<ArrayList<Users>> = _users

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    fun getSearchUser(query: String) {
        ApiConfig.getApiService()
            .getSearchUser(query)
            .enqueue(object: Callback<AllUsers>{
                override fun onResponse(call: Call<AllUsers>, response: Response<AllUsers>) {
                    if (response.isSuccessful) {
                        _users.value = response.body()?.items
                        _isEmpty.value = response.body()?.items?.isEmpty() == true
                    } else {
                        _isEmpty.value = true
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<AllUsers>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    _isEmpty.value = true
                }

            })
    }
}