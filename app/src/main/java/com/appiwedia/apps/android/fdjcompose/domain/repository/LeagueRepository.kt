package com.appiwedia.apps.android.fdjcompose.domain.repository

import com.appiwedia.apps.android.fdjcompose.domain.models.Leagues
import com.appiwedia.apps.android.fdjcompose.domain.models.Teams

interface LeagueRepository {

    suspend fun getAllLeagues(): Leagues

    suspend fun getAllTeams(strLeague: String): Teams

    suspend fun getTeamDetailByName(teamName: String): Teams
}