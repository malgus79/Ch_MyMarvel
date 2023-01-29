package com.example.mymarvel.domain.repository

import com.example.mymarvel.domain.model.CharacterModel

interface InterfaceRepository {
    suspend fun getAllCharacters(offset: Int): List<CharacterModel>
}