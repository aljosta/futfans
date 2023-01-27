package com.example.futfans.domain.usecase

import com.example.futfans.domain.model.TeamDto
import com.example.futfans.domain.repository.FootballRepository
import com.example.futfans.value
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val repository: FootballRepository
) : UseCase<GetTeamsUseCase.Params, List<TeamDto>> {
    override suspend fun execute(params: Params?): List<TeamDto> =
        repository.getTeamByCountry(params?.countryName.value())

    data class Params(
        val countryName: String
    )
}
