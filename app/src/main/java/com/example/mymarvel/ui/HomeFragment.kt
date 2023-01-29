package com.example.mymarvel.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mymarvel.R
import com.example.mymarvel.core.Resource
import com.example.mymarvel.core.common.hide
import com.example.mymarvel.core.common.show
import com.example.mymarvel.core.common.showToast
import com.example.mymarvel.databinding.FragmentHomeBinding
import com.example.mymarvel.ui.adapter.HomeAdapter
import com.example.mymarvel.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeAdapter = HomeAdapter()
        setupCharacterList()

        return binding.root
    }

    private fun setupCharacterList() {
        viewModel.getAllCharactersData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d("STATUSSS", "Loading")
                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    Log.d("STATUSSS", "OK")
                    binding.progressBar.hide()
                    if (it.data.isEmpty()) {
                        binding.rvCharacterList.hide()
                        return@observe
                    }
                    setupRecyclerView()
                    loadData()
                }
                is Resource.Failure -> {
                    binding.progressBar.hide()
                    showToast(getString(R.string.error_detail) + it.exception)
                    Log.d("STATUSSS", "${it.exception}")
                }
            }
        }
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.rvCharacterList.apply {
            adapter = homeAdapter
            layoutManager = StaggeredGridLayoutManager(resources.getInteger(R.integer.main_columns), GridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            show()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                Log.d("aaa", "load: $it")
                homeAdapter.submitData(it)
            }
        }
    }
}


