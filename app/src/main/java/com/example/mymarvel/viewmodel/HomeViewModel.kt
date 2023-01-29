package com.example.mymarvel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mymarvel.core.Resource
import com.example.mymarvel.domain.repository.RepositoryImpl
import com.example.mymarvel.ui.MarvelListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

//    fun fetchAllCharacters(offset: Int) = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
//        emit(Resource.Loading)
//        try {
//            emit(Resource.Success<Nothing>(repository.getAllCharacters(offset)))
//        } catch (e: Exception) {
//            emit(Resource.Failure(e))
//        }
//    }

    private val marvelValue = MutableStateFlow(MarvelListState())
    var _marvelValue: StateFlow<MarvelListState> = marvelValue

    fun getAllCharactersData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllCharacters(offset = offset).collectLatest {
            when (it) {
                is Resource.Success -> {
                    marvelValue.value = MarvelListState(characterModelList = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    marvelValue.value = MarvelListState(isLoading = true)
                }
                is Resource.Failure -> {
                    marvelValue.value = MarvelListState(error = it.exception.toString())
                }
            }
        }
    }
}