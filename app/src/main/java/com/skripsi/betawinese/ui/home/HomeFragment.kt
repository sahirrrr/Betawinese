package com.skripsi.betawinese.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.skripsi.betawinese.core.data.source.remote.network.ApiResponse
import com.skripsi.betawinese.databinding.FragmentHomeBinding
import com.skripsi.betawinese.ui.adapter.BudayaAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private val viewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budayaList()
    }

    private fun budayaList() {
        val budayaAdapter = BudayaAdapter()

        viewModel.getBudayaList().observe(viewLifecycleOwner) { budayaList ->
            if (budayaList != null) {
                when (budayaList) {
                    is ApiResponse.Empty -> {
                        binding?.progressBar?.visibility = View.GONE
                    }
                    is ApiResponse.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), "Failed to load", Toast.LENGTH_SHORT).show()
                    }
                    is ApiResponse.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val dataArray = budayaList.data
                        budayaAdapter.setData(dataArray)
                        budayaAdapter.notifyDataSetChanged()
                        Log.d("data", "budayaList: $dataArray")
                    }
                }
            }
        }

        with(binding?.rvBudaya) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = budayaAdapter
        }
    }

}