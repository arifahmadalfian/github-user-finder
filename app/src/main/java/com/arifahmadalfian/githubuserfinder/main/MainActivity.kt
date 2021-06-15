package com.arifahmadalfian.githubuserfinder.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifahmadalfian.githubuserfinder.adapter.IOnUsersItemsClickListener
import com.arifahmadalfian.githubuserfinder.adapter.UserAdapter
import com.arifahmadalfian.githubuserfinder.databinding.ActivityMainBinding
import com.arifahmadalfian.githubuserfinder.network.model.Users

class MainActivity : AppCompatActivity(), IOnUsersItemsClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        getListUser()

        mainViewModel.isEmpty.observe(this, {
            binding?.emptyLayout?.root?.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun getListUser() {
        adapter = UserAdapter(this)
        adapter.notifyDataSetChanged()

        binding?.apply {
            rvGithubFinder.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGithubFinder.setHasFixedSize(true)
            rvGithubFinder.adapter = adapter

            search.setOnClickListener {
                searchUser()
            }
            etQuery.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        mainViewModel.users.observe(this, {
            if (it != null) {
                adapter.setUser(it)
                showLoad(false)
            }
        })

    }

    private fun searchUser() {
        binding?.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoad(true)
            mainViewModel.getSearchUser(query)
        }
    }

    private fun showLoad(state: Boolean) {
        if (state) {
            binding?.pgBar?.visibility = View.VISIBLE
        } else {
            binding?.pgBar?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onUsersItemClickListener(users: Users, position: Int) {
        Toast.makeText(this@MainActivity, users.login, Toast.LENGTH_SHORT).show()
    }
}