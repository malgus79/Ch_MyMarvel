package com.example.mymarvel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mymarvel.core.Resource
import com.example.mymarvel.core.common.Constants.PAGE_INDEX
import com.example.mymarvel.domain.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    val listData = repository.listDataRepository.cachedIn(viewModelScope)

    fun getAllCharactersData() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(repository.getAllCharacters(PAGE_INDEX)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}