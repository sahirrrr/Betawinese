package com.skripsi.betawinese.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.skripsi.betawinese.R
import com.skripsi.betawinese.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        root = binding?.root

        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to Home Fragment
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 3000)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}