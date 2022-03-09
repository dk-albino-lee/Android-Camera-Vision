package com.dongkeun.camerawithvision.camera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dongkeun.camerawithvision.R
import com.dongkeun.camerawithvision.databinding.AdapterLabelBinding
import com.dongkeun.camerawithvision.model.Label

class LabelAdapter: RecyclerView.Adapter<LabelAdapter.ViewHolder>() {
    var list = mutableListOf<Label>()

    inner class ViewHolder(val binding: AdapterLabelBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Label) {
            binding.label = item
            binding.labelCheckCircle.setImageDrawable(
                if (item.checked) ContextCompat.getDrawable(itemView.context, R.drawable.ic_circle_checked)
                else ContextCompat.getDrawable(itemView.context, R.drawable.ic_circle_unchecked)
            )
            itemView.setOnClickListener {
                item.checked = !item.checked
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}