package com.example.futfans.domain.usecase

import com.example.futfans.domain.model.CountryDto
import com.example.futfans.domain.repository.FootballRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: FootballRepository
) : UseCase<Any, Flow<List<CountryDto>>> {
    override suspend fun execute(params: Any?): Flow<List<CountryDto>> =
        repository.getAllCountries()
}
