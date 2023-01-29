package com.example.mymarvel.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mymarvel.domain.model.CharacterModel
import com.example.mymarvel.model.paging.DataPagingSource
import com.example.mymarvel.model.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    InterfaceRepository {

    val listDataRepository = Pager(
        config = PagingConfig(1),
    ) {
        DataPagingSource(repository = RepositoryImpl(remoteDataSource))
    }.flow

    override suspend fun getAllCharacters(offset: Int): List<CharacterModel> {
        return remoteDataSource.getAllCharacters(offset).data.results.map {
            it.toCharacter()
        }
    }
}



