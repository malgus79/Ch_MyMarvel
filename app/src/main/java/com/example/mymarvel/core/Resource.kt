package com.example.mymarvel.core

import com.example.mymarvel.domain.model.CharacterModel

sealed class Resource<out T>() {
    object Loading : Resource<Nothing>()
    class Success<out T>(val data: List<CharacterModel>) : Resource<T>()
    class Failure(val exception: Exception) : Resource<Nothing>()
}