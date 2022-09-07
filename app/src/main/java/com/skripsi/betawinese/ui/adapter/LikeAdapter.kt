package com.skripsi.betawinese.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import com.skripsi.betawinese.databinding.ItemLikeBinding

class LikeAdapter:RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    private var favoriteList = ArrayList<DetailEntity>()

    fun setData(newFavoriteList: List<DetailEntity>?) {
        if (newFavoriteList == null) return
        favoriteList.clear()
        favoriteList.addAll(newFavoriteList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val binding = ItemLikeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LikeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val data = favoriteList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = favoriteList.size

    inner class LikeViewHolder(private val binding: ItemLikeBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(favorite: DetailEntity) {
            with(binding) {
                tvItemLike.text = "Anda menyukai ${favorite.name}"
            }
        }
    }
}