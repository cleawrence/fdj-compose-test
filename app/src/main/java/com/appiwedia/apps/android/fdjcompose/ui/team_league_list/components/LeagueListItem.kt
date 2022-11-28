package com.appiwedia.apps.android.fdjcompose.ui.team_league_list.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun LeagueListItem(
    league: String,
    onItemClick: (String) -> Unit,
) {
    Text(
        modifier = Modifier
            .clickable { onItemClick(league) },
        text = league,
        color = Color.Red
    )
}