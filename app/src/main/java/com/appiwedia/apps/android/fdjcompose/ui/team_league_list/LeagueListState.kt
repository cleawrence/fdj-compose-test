package com.appiwedia.apps.android.fdjcompose.ui.team_league_list

data class LeagueListState(
    val isLoading: Boolean = false,
    val leagues: List<String> = emptyList(),
    val error: String = "",
)