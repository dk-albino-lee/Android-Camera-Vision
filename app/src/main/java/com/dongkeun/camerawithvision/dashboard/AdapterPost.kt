package com.dongkeun.camerawithvision.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongkeun.camerawithvision.databinding.AdapterPostBinding
import com.dongkeun.camerawithvision.model.Post

class AdapterPost(val viewModel: DashboardViewModel) : RecyclerView.Adapter<AdapterPost.ViewHolder>() {
    var list: MutableList<Post> = mutableListOf()

    inner class ViewHolder(val binding: AdapterPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.post = item
            binding.postImage.setImageBitmap(item.bitmap)
            itemView.setOnLongClickListener {
                viewModel.addFavoritePost()

                return@setOnLongClickListener false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}