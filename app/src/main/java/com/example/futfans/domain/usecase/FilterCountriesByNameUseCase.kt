package com.example.futfans.domain.usecase

import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.repository.FootballRepository
import com.example.futfans.value
import javax.inject.Inject
class FilterCountriesByNameUseCase @Inject constructor(
    private val repository: FootballRepository
) : UseCase<FilterCountriesByNameUseCase.Params, List<CountryDto>> {
    override suspend fun execute(params: Params?): List<CountryDto> =
        repository.filterCountriesByName(params?.keyword.value())

    data class Params(
        val keyword: String,
    )
}

