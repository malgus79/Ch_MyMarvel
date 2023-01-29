package com.example.mymarvel.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mymarvel.core.common.Constants.PAGE_INDEX
import com.example.mymarvel.domain.model.CharacterModel
import com.example.mymarvel.domain.repository.RepositoryImpl
import retrofit2.HttpException
import java.io.IOException

class DataPagingSource(private val repository: RepositoryImpl) : PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, CharacterModel> {

        return try {
            val currentPage = params.key ?: PAGE_INDEX
            val response = repository.getAllCharacters(currentPage)
            val responseData = mutableListOf<CharacterModel>()
            val data = response
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == PAGE_INDEX) null else -1,
                nextKey = currentPage.plus(20)
                //nextKey = if (responseData.isEmpty()) null else currentPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}