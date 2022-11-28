package com.appiwedia.apps.android.fdjcompose.data.remote.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaguesResponse(
    @Json(name = "leagues")
    val leagues: List<LeagueDto>,
) : Parcelable

