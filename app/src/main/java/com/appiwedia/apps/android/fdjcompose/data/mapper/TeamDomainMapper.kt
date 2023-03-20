package com.appiwedia.apps.android.fdjcompose.data.mapper

import com.appiwedia.apps.android.fdjcompose.common.BaseDomainMapper
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamsResponse
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import com.appiwedia.apps.android.fdjcompose.domain.models.Teams

class TeamDomainMapper : BaseDomainMapper<TeamsResponse, Teams>() {

    override fun toDomain(dto: TeamsResponse): Teams {
        val teamsDomain = dto.teams.map { teamDto ->
            Team(
                strTeam = teamDto.strTeam,
                strLeague = teamDto.strLeague,
                strCountry = teamDto.strCountry,
                strTeamBanner = teamDto.strTeamBanner,
                strTeamBadge = teamDto.strTeamBadge,
                strDescriptionEn = teamDto.strDescriptionEN
            )
        }
        return Teams(teamsDomain)
    }
}