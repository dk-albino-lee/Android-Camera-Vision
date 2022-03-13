package com.dongkeun.camerawithvision.etc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dongkeun.camerawithvision.databinding.FragmentEtcBinding

class EtcFragment : Fragment() {

    private lateinit var etcViewModel: EtcViewModel
    private var _binding: FragmentEtcBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEtcBinding.inflate(inflater, container, false)
        initFragment()

        return binding.root
    }

    private fun initFragment() {
        etcViewModel = ViewModelProvider(this)[EtcViewModel::class.java]
        binding.viewModel = etcViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}