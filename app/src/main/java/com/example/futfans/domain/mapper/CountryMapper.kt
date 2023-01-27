package com.example.futfans.domain.mapper

import com.example.futfans.data.api.models.country.CountryEntity
import com.example.futfans.data.database.models.Country
import com.example.futfans.domain.model.CountryDto
import com.example.futfans.presentation.model.CountryModel
import com.example.futfans.value

object CountryMapper {
    fun transformApiModelToDto(countryEntity: CountryEntity) =
        with(countryEntity) {
            CountryDto(countryId = id, name = name, code = code, flag = flag)
        }

    fun transformDtoToApiModel(countryDto: CountryDto) =
        with(countryDto) {
            CountryEntity(id = countryId.value(), name = name, code = code.value(), flag = flag)
        }

    fun transformDtoToModel(countryDto: CountryDto) =
        with(countryDto) {
            CountryModel(name = name.value(), image = flag.value())
        }

    fun transformDBModelToDto(country: Country) =
        with(country) {
            CountryDto(countryId = id, name = name, code = code, flag = flag)
        }

    fun transformDtoToDBModel(countryDto: CountryDto) =
        with(countryDto) {
            Country(id = countryId.value(), name = name.value(), code = code.value(), flag = flag.value())
        }

    fun transformCollectionApiModelToDto(countries: List<CountryEntity>) =
        countries.map { transformApiModelToDto(it) }

    fun transformCollectionDtoToApiModel(countries: List<CountryDto>) =
        countries.map { transformDtoToApiModel(it) }

    fun transformCollectionDtoToModel(countries: List<CountryDto>) =
        countries.map { transformDtoToModel(it) }

    fun transformCollectionDtoToDBModel(countries: List<CountryDto>) =
        countries.map {
            transformDtoToDBModel(it)
        }

    fun transformCollectionDBModelToDto(countries: List<Country>) =
        countries.map {
            transformDBModelToDto(it)
        }
}
