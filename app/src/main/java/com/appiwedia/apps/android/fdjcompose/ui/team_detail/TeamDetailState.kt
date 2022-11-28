package com.appiwedia.apps.android.fdjcompose.ui.team_detail

import com.appiwedia.apps.android.fdjcompose.domain.models.Team

data class TeamDetailState(
    val isLoading: Boolean = false,
    val teamDetail: Team? = null,
    val error: String = "",
)