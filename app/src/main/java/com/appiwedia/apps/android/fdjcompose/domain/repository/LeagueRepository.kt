package com.appiwedia.apps.android.fdjcompose.domain.repository

import com.appiwedia.apps.android.fdjcompose.data.remote.dto.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.TeamDto
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.TeamsResponse

interface LeagueRepository {

    suspend fun getAllLeagues(): LeaguesResponse

    suspend fun getAllTeams(strLeague: String): TeamsResponse

    suspend fun getTeamDetailByName(teamName: String): List<TeamDto>
}