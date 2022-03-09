package com.dongkeun.camerawithvision.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dongkeun.camerawithvision.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private val viewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private var adapter: AdapterPost? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        initFragment()

        viewModel.postList.observe(viewLifecycleOwner) {
            if (binding.postRecycler.adapter == null) binding.postRecycler.adapter = adapter
            (binding.postRecycler.adapter as AdapterPost).list = it
            binding.postRecycler.adapter?.notifyDataSetChanged() // 이후 수정
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFragment() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = AdapterPost(viewModel)
    }
}