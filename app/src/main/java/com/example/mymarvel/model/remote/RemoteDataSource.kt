package com.example.mymarvel.model.remote

import com.example.mymarvel.core.Constants.API_KEY
import com.example.mymarvel.core.Constants.hash
import com.example.mymarvel.core.Constants.timestamp
import com.example.mymarvel.model.data.CharacterDTO
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllCharacters(): CharacterDTO {
        return apiService.getAllCharacters(API_KEY, timestamp, hash())
    }
}