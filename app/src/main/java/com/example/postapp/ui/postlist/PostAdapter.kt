package com.example.postapp.ui.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.postapp.R
import com.example.postapp.data.model.Post
import com.example.postapp.databinding.ItemPostBinding

class PostAdapter(
    private val onItemClick: (Post) -> Unit
) : ListAdapter<Post,PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.textTitle.text = post.title
            binding.textBody.text = post.body

            binding.imagePost.load(post.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_image_place_holder)
                error(R.drawable.ic_image_place_holder)
                transformations(CircleCropTransformation())
            }

            binding.root.setOnClickListener { onItemClick(post) }
        }
    }
    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem == newItem
    }
}