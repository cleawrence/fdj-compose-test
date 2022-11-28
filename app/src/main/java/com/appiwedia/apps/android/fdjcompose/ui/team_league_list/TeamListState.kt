package com.appiwedia.apps.android.fdjcompose.ui.team_league_list

import com.appiwedia.apps.android.fdjcompose.domain.models.Team

data class TeamListState(
    val isLoading: Boolean = false,
    val teams: List<Team> = emptyList(),
    val error: String = "",
)
