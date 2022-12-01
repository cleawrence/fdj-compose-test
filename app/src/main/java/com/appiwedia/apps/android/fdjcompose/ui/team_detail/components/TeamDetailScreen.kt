package com.appiwedia.apps.android.fdjcompose.ui.team_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.appiwedia.apps.android.fdjcompose.R
import com.appiwedia.apps.android.fdjcompose.ui.team_detail.TeamDetailViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamDetailScreen(
    navController: NavController,
    viewModel: TeamDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.teamDetailState.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.teamDetail?.let { team ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    stickyHeader {
                        TopAppBar(
                            title = { Text(team.strTeam) },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "navigate back"
                                    )
                                }
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    item {
                        AsyncImage(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillBounds,
                            model = ImageRequest.Builder(LocalContext.current)
                                .crossfade(true)
                                .data(team.strTeamBanner)
                                .build(),
                            contentDescription = buildString {
                                append(stringResource(R.string.teambanner_of))
                                append(team.strTeam)
                            },
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                        ) {
                            TextDetail(text = team.strCountry)
                            TextDetail(
                                text = team.strLeague,
                                fontWeight = FontWeight.Bold
                            )
                            team.strDescriptionEn?.let {
                                TextDetail(
                                    text = it
                                )
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
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

@Composable
fun TextDetail(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        modifier = modifier.padding(vertical = 5.dp),
        text = text,
        fontWeight = fontWeight
    )
}