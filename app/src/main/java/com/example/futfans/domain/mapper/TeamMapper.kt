package com.example.futfans.domain.mapper

import com.example.futfans.data.api.models.team.TeamEntity
import com.example.futfans.data.api.models.team.TeamInformationEntity
import com.example.futfans.data.api.models.team.VenueEntity
import com.example.futfans.data.database.models.Team
import com.example.futfans.domain.model.StadiumDto
import com.example.futfans.domain.model.TeamDto
import com.example.futfans.presentation.model.TeamModel
import com.example.futfans.value

object TeamMapper {
    fun transformEntityToDto(teamEntity: TeamEntity) = with(teamEntity) {
        TeamDto(
            teamId = team?.id,
            name = team?.name,
            logo = team?.logo,
            founded = team?.founded,
            country = team?.country,
            code = team?.code,
            stadium = StadiumDto(
                stadiumId = venue?.id,
                name = venue?.name,
                address = venue?.address,
                city = venue?.city,
                surface = venue?.surface,
                image = venue?.image,
            )
        )
    }

    fun transformDtoToEntity(teamDto: TeamDto) = with(teamDto) {
        TeamEntity(
            team = TeamInformationEntity(
                id = teamId,
                name = name,
                logo = logo,
                founded = founded,
                country = country,
                code = code,
            ),
            venue = VenueEntity(
                id = stadium?.stadiumId,
                name = stadium?.name,
                address = stadium?.address,
                city = stadium?.city,
                surface = stadium?.surface,
                image = stadium?.image,
            )
        )
    }

    fun transformDtoToModel(teamDto: TeamDto) = with(teamDto) {
        TeamModel(
            name = name.value(),
            image = logo.value(),
            founded = founded.value(),
            stadiumName = stadium?.name.value(),
        )
    }

    fun transformDBModelToDto(team: Team) = with(team) {
        TeamDto(
            teamId = id,
            name = name,
            logo = logo,
            founded = team.founded,
            country = country,
            code = code,
            stadium = StadiumDto(
                stadiumId = stadiumId,
                name = stadiumName,
                address = stadiumAddress,
                city = stadiumCity,
                surface = stadiumSurface,
                image = stadiumImage,
            )
        )
    }

    fun transformDtoToDBModel(teamDto: TeamDto) = with(teamDto) {
        Team(
            id = teamId.value(),
            name = name.value(),
            code = code.value(),
            country = country.value(),
            founded = founded.value(),
            logo = logo.value(),
            stadiumId = stadium?.stadiumId.value(),
            stadiumName = stadium?.name.value(),
            stadiumAddress = stadium?.address.value(),
            stadiumCity = stadium?.city.value(),
            stadiumCapacity = stadium?.capacity.value(),
            stadiumSurface = stadium?.surface.value(),
            stadiumImage = stadium?.image.value(),
        )
    }

    fun transformCollectionEntityToDto(teams: List<TeamEntity>) =
        teams.map { entity ->
            transformEntityToDto(entity)
        }

    fun transformCollectionDtoToEntity(teams: List<TeamDto>) =
        teams.map { dto ->
            transformDtoToEntity(dto)
        }

    fun transformCollectionDtoToModel(teams: List<TeamDto>) =
        teams.map { dto ->
            transformDtoToModel(dto)
        }
}
