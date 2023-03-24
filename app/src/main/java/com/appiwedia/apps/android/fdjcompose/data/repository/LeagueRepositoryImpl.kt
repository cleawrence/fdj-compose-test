package com.appiwedia.apps.android.fdjcompose.data.repository

import com.appiwedia.apps.android.fdjcompose.data.mapper.toLeagues
import com.appiwedia.apps.android.fdjcompose.data.mapper.toTeams
import com.appiwedia.apps.android.fdjcompose.data.service.LeagueServiceApi
import com.appiwedia.apps.android.fdjcompose.domain.models.Leagues
import com.appiwedia.apps.android.fdjcompose.domain.models.Teams
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val api: LeagueServiceApi
) : LeagueRepository {

    override suspend fun getAllLeagues(): Leagues {
        return api.getAllLeagues().toLeagues()
    }

    override suspend fun getAllTeams(strLeague: String): Teams {
        return api.getAllTeams(strLeague).toTeams()
    }

    override suspend fun getTeamDetailByName(teamName: String): Teams {
        return api.getTeamDetailByName(teamName).toTeams()
    }
}