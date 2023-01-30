package com.example.mymarvel.model.local

import com.example.mymarvel.domain.model.CharacterModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val marvelDao: MarvelDao) {

    suspend fun getAllCharacters(): List<CharacterModel> {
        return marvelDao.getAllCharacters().toCharacterModel()
    }

    suspend fun saveCharacter(character: CharacterModelEntity) {
        marvelDao.saveCharacter(character)
    }
}






