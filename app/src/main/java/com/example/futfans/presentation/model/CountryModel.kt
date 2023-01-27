package com.example.futfans.presentation.model

data class CountryModel(
    val name: String,
    val image: String,
)

fun getCountryList() = listOf<CountryModel>(
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
    CountryModel("Alemania", ""),
)
