package com.appiwedia.apps.android.fdjcompose.domain.use_case.data

import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeagueDto
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.squareup.moshi.Moshi


val league1 = LeagueDto(
    idLeague = "4328",
    strLeague = "English Premier League",
    strSport = "Soccer",
    strLeagueAlternate = "Premier League"
)
val league2 = LeagueDto(
    idLeague = "4328",
    strLeague = "English Premier League",
    strSport = "Soccer",
    strLeagueAlternate = "Premier League"
)
val league3 = LeagueDto(
    idLeague = "4329",
    strLeague = "English League Championship",
    strSport = "Soccer",
    strLeagueAlternate = "Championship"
)
val league4 = LeagueDto(
    idLeague = "4330",
    strLeague = "Scottish Premier League",
    strSport = "Soccer",
    strLeagueAlternate = "Scottish Premiership"
)
val league5 = LeagueDto(
    idLeague = "4331",
    strLeague = "German Bundesliga",
    strSport = "Soccer",
    strLeagueAlternate = "Bundesliga, Fußball-Bundesliga"
)
val league6 = LeagueDto(
    idLeague = "4332",
    strLeague = "Italian Serie A",
    strSport = "Soccer",
    strLeagueAlternate = "Serie A"
)

val leagues = listOf(league1, league2, league3, league4, league5, league6)

object FakeLeaguesData {
    fun build(): Pair<String, LeaguesResponse> {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(LeaguesResponse::class.java)
        val leaguesResponse = LeaguesResponse(leagues)
        return Pair(adapter.toJson(leaguesResponse), leaguesResponse)
    }
}