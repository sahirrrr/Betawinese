package com.skripsi.betawinese.ui.like

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skripsi.betawinese.databinding.FragmentLikeBinding
import com.skripsi.betawinese.ui.adapter.LikeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LikeFragment : Fragment() {
    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LikeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteList()
    }

    private fun favoriteList() {
       val likeAdapter = LikeAdapter()

        viewModel.getFavoriteList().observe(viewLifecycleOwner) { favoriteList ->
            if (favoriteList == null || favoriteList.isEmpty()) {
                binding?.ivEmptyStateLike?.visibility = View.VISIBLE
                binding?.tvEmptyStateLike?.visibility = View.VISIBLE
            } else {
                binding?.ivEmptyStateLike?.visibility = View.GONE
                binding?.tvEmptyStateLike?.visibility = View.GONE
                likeAdapter.setData(favoriteList)
                likeAdapter.notifyDataSetChanged()
            }
        }

        with(binding?.rvLike) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = likeAdapter
        }
    }


}