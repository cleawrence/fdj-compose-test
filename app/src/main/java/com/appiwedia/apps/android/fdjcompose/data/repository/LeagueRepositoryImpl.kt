package com.appiwedia.apps.android.fdjcompose.data.repository

import com.appiwedia.apps.android.fdjcompose.data.service.LeagueServiceApi
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamsResponse
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val api: LeagueServiceApi,
) : LeagueRepository {

    override suspend fun getAllLeagues(): LeaguesResponse {
        return api.getAllLeagues()
    }

    override suspend fun getAllTeams(strLeague: String): TeamsResponse {
        return api.getAllTeams(strLeague)
    }

    override suspend fun getTeamDetailByName(teamName: String): TeamsResponse {
        return api.getTeamDetailByName(teamName)
    }
}