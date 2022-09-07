package com.skripsi.betawinese.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.betawinese.databinding.ItemCommentBinding
import com.skripsi.betawinese.domain.model.CommentModel

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var commentList = ArrayList<CommentModel>()

    fun setDataList(newListCommentData: List<CommentModel>?) {
        if (newListCommentData == null) return
        commentList.clear()
        commentList.addAll(newListCommentData)
    }

    fun setData(newComment: CommentModel) {
        commentList.add(newComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val data = commentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = commentList.size

    class CommentViewHolder(private val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentModel) {
            with(binding) {
                tvCommentName.text = comment.name
                tvCommentMessage.text = comment.message
            }
        }
    }
}