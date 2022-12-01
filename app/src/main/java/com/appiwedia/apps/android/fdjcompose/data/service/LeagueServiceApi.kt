package com.appiwedia.apps.android.fdjcompose.data.service

import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueServiceApi {
    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeaguesResponse

    @GET("search_all_teams.php")
    suspend fun getAllTeams(@Query("l") strLeague: String): TeamsResponse

    @GET("searchteams.php")
    suspend fun getTeamDetailByName(@Query("t") teamName: String): TeamsResponse

}