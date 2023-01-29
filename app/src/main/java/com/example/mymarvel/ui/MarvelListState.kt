package com.example.mymarvel.ui

import com.example.mymarvel.domain.model.CharacterModel


data class MarvelListState(
    val isLoading: Boolean = false,
    val characterModelList: List<CharacterModel> = emptyList(),
    val error: String = ""
)