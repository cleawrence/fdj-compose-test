package com.appiwedia.apps.android.fdjcompose.presentation.team_league_list.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appiwedia.apps.android.fdjcompose.ui.team_league_list.TeamsViewModel
import com.appiwedia.apps.android.fdjcompose.ui.team_league_list.components.LeagueListItem

@Composable
fun LeagueListScreen(
    navController: NavController,
    viewModel: TeamsViewModel = hiltViewModel(),
) {
    val state = viewModel.leagueState.value
    val teamsState = viewModel.teamState.value
    val activity = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.leagues) { league ->
                LeagueListItem(
                    league = league,
                    onItemClick = {
                        Toast.makeText(activity, league, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

