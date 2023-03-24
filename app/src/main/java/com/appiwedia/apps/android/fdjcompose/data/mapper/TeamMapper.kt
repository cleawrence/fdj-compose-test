package com.appiwedia.apps.android.fdjcompose.data.mapper

import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamDto
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamsResponse
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import com.appiwedia.apps.android.fdjcompose.domain.models.Teams

fun TeamDto.toTeam() : Team {
    return Team(
        strTeam = this.strTeam,
        strTeamBanner = this.strTeamBanner,
        strDescriptionEn = this.strDescriptionEN,
        strCountry = this.strCountry,
        strLeague = this.strLeague,
        strTeamBadge = this.strTeamBadge
    )
}

fun TeamsResponse.toTeams(): Teams {
    return Teams(
        teams = this.teams.map { it.toTeam() }
    )
}