package com.skripsi.betawinese.ui.adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.databinding.ItemBudayaBinding
import com.skripsi.betawinese.ui.detail.DetailActivity

class BudayaAdapter: RecyclerView.Adapter<BudayaAdapter.BudayaViewHolder>() {

    private var budayaList = ArrayList<BudayaResponse>()

    fun setData(newBudayaListData : List<BudayaResponse>?) {
        if (newBudayaListData == null) return
        budayaList.clear()
        budayaList.addAll(newBudayaListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudayaViewHolder {
        val binding = ItemBudayaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudayaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudayaViewHolder, position: Int) {
        val data = budayaList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = budayaList.size

    inner class BudayaViewHolder(private val binding: ItemBudayaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(budaya: BudayaResponse) {
            with(binding) {
                tvBudayaTitle.text = budaya.name
                tvBudayaDesc.text = budaya.description
                Glide.with(itemView.context)
                    .load(budaya.photo)
                    .into(ivBudaya)
                viewBudaya.backgroundTintList = ColorStateList.valueOf(Color.parseColor(budaya.color))

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, budaya)
                    itemView.context.startActivity(intent)
                }

                btnSelengkapnya.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, budaya)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}