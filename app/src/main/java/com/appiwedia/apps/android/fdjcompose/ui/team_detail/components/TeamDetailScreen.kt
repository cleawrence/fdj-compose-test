package com.appiwedia.apps.android.fdjcompose.ui.team_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appiwedia.apps.android.fdjcompose.ui.team_detail.TeamDetailViewModel


@Composable
fun TeamDetailScreen(
    viewModel: TeamDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.teamDetailState.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.teamDetail?.let { team ->
            Column {
                Text(text = team.strTeam)
                Text(text = team.strCountry)
                Text(text = team.strLeague)
                team.strDescriptionEn?.let { Text(text = it) }
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
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}