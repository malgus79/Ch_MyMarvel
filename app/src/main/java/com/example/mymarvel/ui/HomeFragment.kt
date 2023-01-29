package com.example.mymarvel.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mymarvel.core.Resource
import com.example.mymarvel.core.common.hide
import com.example.mymarvel.core.common.show
import com.example.mymarvel.databinding.FragmentHomeBinding
import com.example.mymarvel.domain.model.CharacterModel
import com.example.mymarvel.ui.adapter.HomeAdapter
import com.example.mymarvel.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private var flag = 3
    private var paginatedValue = 0
    private lateinit var recyclerView:RecyclerView
    private lateinit var layoutManager: GridLayoutManager

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        recyclerView = binding.rvCharacterList
        layoutManager = GridLayoutManager(requireContext(), 2)
        setupRecyclerView()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount-1) {
                    paginatedValue += 20
                    viewModel.getAllCharactersData(paginatedValue)
                    callApi()
                }
            }
        })

//        setupCharacterList()

        return binding.root
    }

    private fun recyclerViewCharacter() {
        homeAdapter = HomeAdapter(requireContext(), ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = homeAdapter
    }

    private fun callApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag) {
                viewModel._marvelValue.collectLatest {
                    when {
                        it.isLoading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() -> {
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        it.characterModelList.isNotEmpty() -> {
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            homeAdapter.setCharacterList(it.characterModelList as ArrayList<CharacterModel>)
                        }
                    }
                    delay(1000)
                }
            }
        }
    }

    private fun setupCharacterList() {
//        viewModel.fetchAllCharacters(paginatedValue).observe(viewLifecycleOwner) {
//            when (it) {
//                is Resource.Loading -> {
//                    Log.d("STATUSSS", "Loading")
//                    binding.emptyContainer.root.hide()
//                    binding.progressBar.show()
//                }
//                is Resource.Success -> {
//                    Log.d("STATUSSS", "OK")
//                    binding.progressBar.hide()
//                    if (it.data.data.results.isEmpty()) {
//                        binding.rvCharacterList.hide()
//                        binding.emptyContainer.root.show()
//                        return@observe
//                    }
//                    setupRecyclerView()
//                    flag = 0
//                   homeAdapter.setCharacterList(it.data.data.results as ArrayList<CharacterModel>)
//                }
//                is Resource.Failure -> {
//                    binding.progressBar.hide()
//
//                    Log.d("STATUSSS", "${it.exception}")
//                }
//            }
//        }
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter(requireContext(), ArrayList())
        binding.rvCharacterList.apply {
            adapter = homeAdapter
            layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            show()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        viewModel.fetchAllCharacters(paginatedValue)
//        setupCharacterList()
//    }
}


