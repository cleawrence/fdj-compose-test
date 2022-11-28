package com.appiwedia.apps.android.fdjcompose.ui


sealed class Screen(val route: String) {
    object LeagueListScreen : Screen("league_list_screen")
    object TeamDetailScreen : Screen("team_detail_screen")
}
