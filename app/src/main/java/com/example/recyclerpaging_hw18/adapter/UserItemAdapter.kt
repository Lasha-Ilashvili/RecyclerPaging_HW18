package com.example.recyclerpaging_hw18.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerpaging_hw18.databinding.UserItemBinding
import com.example.recyclerpaging_hw18.model.User

class UserItemAdapter : ListAdapter<User, UserItemAdapter.UserItemViewHolder>(ChatITemDiffUtil) {

    object ChatITemDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind()
    }

    inner class UserItemViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val user = currentList[adapterPosition]

            with(binding) {
                tvEmail.text = user.email
                tvUsername.text = "${user.firstName} ${user.lastName}"

                Glide.with(itemView.context)
                    .load(user.avatar)
                    .into(ivAvatar)
            }
        }
    }
}