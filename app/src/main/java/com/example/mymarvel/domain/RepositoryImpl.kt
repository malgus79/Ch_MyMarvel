package com.example.mymarvel.domain

import com.example.mymarvel.model.data.CharacterDTO
import com.example.mymarvel.model.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAllCharacters(): CharacterDTO {
        return remoteDataSource.getAllCharacters()
    }
}

