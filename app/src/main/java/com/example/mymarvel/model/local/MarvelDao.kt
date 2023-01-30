package com.example.mymarvel.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelDao {

    @Query("SELECT * FROM character_entity")
    suspend fun getAllCharacters(): List<CharacterModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(character: CharacterModelEntity)
}