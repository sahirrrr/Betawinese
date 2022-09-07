package com.skripsi.betawinese.ui.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skripsi.betawinese.R
import com.skripsi.betawinese.databinding.ItemDetailBinding
import com.skripsi.betawinese.domain.model.DetailModel
import com.skripsi.betawinese.ui.detail.DetailViewModel

class DetailAdapter(private var viewModel: DetailViewModel, private var budayaColor: String) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var detailList = ArrayList<DetailModel>()

    fun setData(newDetailListData: List<DetailModel>?) {
        if (newDetailListData == null) return
        detailList.clear()
        detailList.addAll(newDetailListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.DetailViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailAdapter.DetailViewHolder, position: Int) {
        val data = detailList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = detailList.size

    inner class DetailViewHolder(private val binding: ItemDetailBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(detail: DetailModel) {
            with(binding) {
                tvDetailTitle.text = detail.name
                tvCreator.text = "Pencipta: ${detail.creator}"
                tvDetailDesc.text = detail.description
                Glide.with(itemView.context)
                    .load(detail.photo)
                    .into(ivDetail)
                setFavorite(ivFavorite, detail.favorite)

                viewDetail.backgroundTintList = ColorStateList.valueOf(Color.parseColor(budayaColor))

                ivFavorite.setOnClickListener {
                    detail.favorite = !detail.favorite
                    setFavorite(ivFavorite, detail.favorite)
                    viewModel.setFavoriteDetail(detail)
                }
            }
        }
    }

    private fun setFavorite(ivFavorite: ImageView, state: Boolean) {
        if (state) {
            ivFavorite.setImageResource(R.drawable.ic_heart_fill)
        } else {
            ivFavorite.setImageResource(R.drawable.ic_heart_empty)
        }
    }
}