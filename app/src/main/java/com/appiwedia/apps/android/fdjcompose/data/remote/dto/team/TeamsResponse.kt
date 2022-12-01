package com.appiwedia.apps.android.fdjcompose.data.remote.dto.team

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamsResponse(
    @Json(name = "teams")
    val teams: List<TeamDto>
) : Parcelable
