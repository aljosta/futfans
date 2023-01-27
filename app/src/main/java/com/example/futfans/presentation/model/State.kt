package com.example.futfans.presentation.model

sealed class State {
    data class Success(val data: List<CountryModel>) : State()
    data class Error(var exception: Throwable) : State()
    object Loading : State()
    object Empty : State()
}
