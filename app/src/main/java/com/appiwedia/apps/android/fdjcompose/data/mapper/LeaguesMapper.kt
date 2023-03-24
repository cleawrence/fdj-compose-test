package com.appiwedia.apps.android.fdjcompose.data.mapper

import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeagueDto
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.domain.models.League
import com.appiwedia.apps.android.fdjcompose.domain.models.Leagues

fun LeagueDto.toLeague(): League {
    return League(
        strLeague = this.strLeague,
        idLeague = this.idLeague,
        strLeagueAlternate = this.strLeagueAlternate,
        strSport = this.strSport
    )
}

fun LeaguesResponse.toLeagues(): Leagues {
    return Leagues(
        leagesUi = this.leagues.map { it.toLeague() }
    )
}