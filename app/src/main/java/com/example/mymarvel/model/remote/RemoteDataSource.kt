package com.example.mymarvel.model.remote

import com.example.mymarvel.core.common.Constants.API_KEY
import com.example.mymarvel.core.common.Constants.hash
import com.example.mymarvel.core.common.Constants.timestamp
import com.example.mymarvel.model.data.CharacterDTO
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllCharacters(offset: Int): CharacterDTO {
        return apiService.getAllCharacters(API_KEY, timestamp, hash(), offset = offset.toString())
    }
}