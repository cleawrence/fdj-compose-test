package com.appiwedia.apps.android.fdjcompose.data.repository

import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamsResponse

interface LeagueRepository {

    suspend fun getAllLeagues(): LeaguesResponse

    suspend fun getAllTeams(strLeague: String): TeamsResponse

    suspend fun getTeamDetailByName(teamName: String): TeamsResponse
}