package com.example.mymarvel.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterModelEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}