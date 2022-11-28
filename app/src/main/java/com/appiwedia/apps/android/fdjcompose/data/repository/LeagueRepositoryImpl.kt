package com.appiwedia.apps.android.fdjcompose.data.repository

import com.appiwedia.apps.android.fdjcompose.data.LeagueServiceApi
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.TeamDto
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.TeamsResponse
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val api: LeagueServiceApi
) : LeagueRepository {

    override suspend fun getAllLeagues(): LeaguesResponse {
        return api.getAllLeagues()
    }

    override suspend fun getAllTeams(strLeague: String): TeamsResponse {
        return api.getAllTeams(strLeague)
    }

    override suspend fun getTeamDetailByName(teamName: String): List<TeamDto> {
        return api.getTeamDetailByName(teamName)
    }
}