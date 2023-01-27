package com.example.futfans.domain.usecase

import com.example.futfans.domain.model.TeamDto
import com.example.futfans.domain.repository.FootballRepository
import com.example.futfans.value
import javax.inject.Inject

class FilterTeamsByNameUseCase @Inject constructor(
    private val repository: FootballRepository
) : UseCase<FilterTeamsByNameUseCase.Params, List<TeamDto>> {
    override suspend fun execute(params: Params?): List<TeamDto> =
        repository.filterTeamsByName(params?.keyword.value(), params?.countryName.value())

    data class Params(
        val keyword: String,
        val countryName: String,
    )
}
