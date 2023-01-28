package com.example.mymarvel.model.remote

import com.example.mymarvel.core.Constants.API_KEY
import com.example.mymarvel.core.Constants.hash
import com.example.mymarvel.core.Constants.timestamp
import com.example.mymarvel.model.data.CharacterDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = timestamp,
        @Query("hash") hash: String = hash(),
        @Query("offset") offset: String
    ): CharacterDTO
}