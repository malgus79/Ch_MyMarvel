package com.example.mymarvel.di

import android.content.Context
import androidx.room.Room
import com.example.mymarvel.core.common.Constants.MARVEL_DATABASE
import com.example.mymarvel.model.local.MarvelDao
import com.example.mymarvel.model.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(context, MarvelDatabase::class.java, MARVEL_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesMarvelDao(marveldb: MarvelDatabase) : MarvelDao {
        return marveldb.marvelDao()
    }

}