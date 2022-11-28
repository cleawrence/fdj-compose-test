package com.appiwedia.apps.android.fdjcompose.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val strTeam: String,
    val strTeamBanner: String?,
    val strDescriptionEn: String?,
    val strCountry: String,
    val strLeague: String,
    val strTeamBadge: String?
) : Parcelable
