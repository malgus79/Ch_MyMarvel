package com.example.mymarvel.domain.repository

import com.example.mymarvel.core.Resource
import com.example.mymarvel.domain.model.CharacterModel
import com.example.mymarvel.model.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) {
//    suspend fun getAllCharacters(offset: Int): CharacterDTO {
//        return remoteDataSource.getAllCharacters(offset = offset)
//    }

    suspend fun getAllCharacters(offset:Int): Flow<Resource<List<CharacterModel>>> = flow {
        try {
            emit(Resource.Loading)

            val list = remoteDataSource.getAllCharacters(offset = offset).data.results.map {
                it.toCharacter()
            }
            emit(Resource.Success(list))
        }
        catch (e: HttpException){
            emit(Resource.Failure(e))
        }
        catch (e: IOException){
            emit(Resource.Failure(e))
        }

    }
}



