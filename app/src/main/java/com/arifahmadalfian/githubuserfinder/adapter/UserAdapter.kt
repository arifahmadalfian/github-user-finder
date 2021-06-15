package com.arifahmadalfian.githubuserfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arifahmadalfian.githubuserfinder.databinding.ItemGithubFinderBinding
import com.arifahmadalfian.githubuserfinder.network.model.Users
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter(
    private var clickListener: IOnUsersItemsClickListener
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users = ArrayList<Users>()

    fun setUser(user: ArrayList<Users>){
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private var binding: ItemGithubFinderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users, action: IOnUsersItemsClickListener){
            with(binding) {
                Glide.with(binding.root)
                    .load(users.avatarUrl)
                    .apply(RequestOptions().override(80, 80))
                    .into(imgUser)
                tvNama.text = users.login
                tvUrlGithub.text = users.htmlUrl

                binding.constraint.setOnClickListener {
                    action.onUsersItemClickListener(users, bindingAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemGithubFinderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position], clickListener)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

interface IOnUsersItemsClickListener {
    fun onUsersItemClickListener(users: Users, position: Int)
}