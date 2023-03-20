package com.appiwedia.apps.android.fdjcompose.data.mapper

import com.appiwedia.apps.android.fdjcompose.common.BaseDomainMapper
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.domain.models.League
import com.appiwedia.apps.android.fdjcompose.domain.models.Leagues

class LeaguesDomainMapper : BaseDomainMapper<LeaguesResponse, Leagues>() {

    override fun toDomain(dto: LeaguesResponse): Leagues {
        val leagues = dto.leagues.map { leagueDto ->
            League(
                idLeague = leagueDto.idLeague,
                strLeague = leagueDto.strLeague,
                strLeagueAlternate = leagueDto.strLeagueAlternate,
                strSport = leagueDto.strSport
            )
        }
        return Leagues(leagues)
    }
}