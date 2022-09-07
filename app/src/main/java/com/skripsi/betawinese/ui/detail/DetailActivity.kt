package com.skripsi.betawinese.ui.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.skripsi.betawinese.R
import com.skripsi.betawinese.core.data.Resource
import com.skripsi.betawinese.core.data.source.remote.network.ApiResponse
import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.utils.DataMapper
import com.skripsi.betawinese.databinding.ActivityDetailBinding
import com.skripsi.betawinese.ui.adapter.CommentAdapter
import com.skripsi.betawinese.ui.adapter.DetailAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // hide action bar
        supportActionBar?.hide()

        window.statusBarColor = getColor(R.color.primary)
        setLightStatusBar(false)

        // Back Pressed
        binding?.ivBack?.setOnClickListener {
            onBackPressed()
        }

        val commentAdapter = CommentAdapter()

        val budaya = intent.getParcelableExtra<BudayaResponse>(EXTRA_ID)
        val kodeBudaya = budaya?.id!! + 100
        val color = budaya.color

        binding?.tvBudayaTitle?.text = budaya.name
        binding?.viewButtonDetail?.backgroundTintList =  ColorStateList.valueOf(Color.parseColor(color))
        binding?.edtMessage?.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        binding?.edtName?.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        binding?.viewBackgroundListComment?.backgroundTintList =  ColorStateList.valueOf(Color.parseColor(color))

        detailList(kodeBudaya, color)
        commentList(commentAdapter, kodeBudaya)


        binding?.btnSend?.setOnClickListener {
            val commentName = binding?.edtName?.text?.toString()
            val commentMessage = binding?.edtMessage?.text?.toString()

            val commentRequest = CommentRequest(
                name = commentName!!,
                message = commentMessage!!,
                kodeBudaya = kodeBudaya
            )

            viewModel.postComment(commentRequest).observe(this) { comment ->
                if (comment != null) {
                    when(comment) {
                        is ApiResponse.Success -> {
                            val data = DataMapper.mapCommentResponseToDomain(comment.data)
                            commentAdapter.setData(data)
                            commentAdapter.notifyDataSetChanged()

                            binding?.edtName?.text?.clear()
                            binding?.edtMessage?.text?.clear()

                            Toast.makeText(this, "Comment Sent !", Toast.LENGTH_SHORT).show()
                        }
                        is ApiResponse.Empty -> {
                            binding?.progressBar?.visibility = View.GONE
                        }
                        is ApiResponse.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun detailList(kodeBudaya: Int, color: String) {
        val detailAdapter = DetailAdapter(viewModel, color)

        viewModel.getDetailList(kodeBudaya).observe(this) { detailList ->
            if (detailList != null) {
                when (detailList) {
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val dataArray = detailList.data
                        detailAdapter.setData(dataArray)
                        detailAdapter.notifyDataSetChanged()
                        Log.d("data", "detailList: $dataArray")
                    }
                }
            }
        }

        with(binding?.rvDetail) {
            this?.layoutManager =  LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL ,false)
            this?.setHasFixedSize(true)
            this?.adapter = detailAdapter
        }
    }

    private fun commentList(commentAdapter: CommentAdapter, kodeBudaya: Int) {
        viewModel.getCommentList(kodeBudaya).observe(this) {commentList ->
            if (commentList != null) {
                when(commentList) {
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.ivEmptyStateComment?.visibility = View.GONE
                        binding?.tvEmptyStateComment?.visibility = View.GONE
                        val dataArray = commentList.data
                        commentAdapter.setDataList(dataArray)
                        commentAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.ivEmptyStateComment?.visibility = View.VISIBLE
                        binding?.tvEmptyStateComment?.visibility = View.VISIBLE
                    }
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                }
            }
        }
        with(binding?.rvComment) {
            val linearLayoutManager =  LinearLayoutManager(this@DetailActivity)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            this?.layoutManager =  linearLayoutManager
            this?.setHasFixedSize(true)
            this?.adapter = commentAdapter
        }
    }

    private fun setLightStatusBar(status: Boolean) {
        if (status){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window?.insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window?.insetsController?.setSystemBarsAppearance(0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }
    }
}