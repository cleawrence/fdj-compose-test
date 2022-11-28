package com.appiwedia.apps.android.fdjcompose.data.remote.dto

import android.os.Parcel
import android.os.Parcelable
import com.appiwedia.apps.android.fdjcompose.domain.models.League
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueDto(
    @Json(name = "idLeague")
    val idLeague: String,
    @Json(name = "strLeague")
    val strLeague: String,
    @Json(name = "strLeagueAlternate")
    val strLeagueAlternate: String? = "",
    @Json(name = "strSport")
    val strSport: String,
) : Parcelable


fun LeagueDto.toLeague(): League {
    return League(
        idLeague = idLeague,
        strLeague = strLeague,
        strLeagueAlternate = strLeagueAlternate,
        strSport = strSport
    )
}