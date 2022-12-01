package com.appiwedia.apps.android.fdjcompose.ui.team_league_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appiwedia.apps.android.fdjcompose.R
import com.appiwedia.apps.android.fdjcompose.ui.Screen
import com.appiwedia.apps.android.fdjcompose.ui.team_league_list.TeamsViewModel
import timber.log.Timber


@Composable
fun LeagueListScreen(
    navController: NavController,
    viewModel: TeamsViewModel = hiltViewModel(),
) {
    val state = viewModel.leagueState.value
    val teamsState = viewModel.teamState.value

    val leagueName = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AutoCompleteSearchBar(
            leagueName = leagueName,
            leagues = state.leagues,
            onCLearText = {
                viewModel.deleteAllTeams()
            },
            onSelectedLeague = { league ->
                viewModel.getTeamsFromLeague(league)
            }
        )

        if (teamsState.teams.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(teamsState.teams) { team ->
                    TeamBadge(
                        modifier = Modifier.padding(8.dp),
                        teamName = team.strTeam,
                        teamBannerUrl = team.strTeamBadge ?: ""
                    ) { teamName ->
                        navController.navigate(Screen.TeamDetailScreen.route + "/${teamName}")
                    }
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
        )
    }

    if (state.isLoading) {
        Timber.tag("loading").d("loading all leagues")
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AutoCompleteSearchBar(
    leagueName: MutableState<String>,
    leagues: List<String>,
    onSelectedLeague: (String) -> Unit,
    onCLearText: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val heightTextFields by remember {
        mutableStateOf(60.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 1.8.dp,
                            color = Color.Green,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    label = { Text(text = stringResource(R.string.search_by_league)) },
                    value = leagueName.value,
                    onValueChange = {
                        leagueName.value = it
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle(
                        color = Color.Red,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            leagueName.value = ""
                            onCLearText.invoke()
                            keyboardController?.hide()
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.Clear,
                                contentDescription = "clear",
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {

                        if (leagueName.value.isNotEmpty()) {
                            items(
                                leagues.filter {
                                    it.lowercase()
                                        .contains(leagueName.value.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                                    .sorted()
                            ) {
                                LeaguesItem(title = it) { title ->
                                    leagueName.value = title
                                    expanded = false
                                    keyboardController?.hide()
                                    onSelectedLeague.invoke(title)
                                }
                            }
                        } else {
                            items(
                                leagues.sorted()
                            ) {
                                LeaguesItem(title = it) { title ->
                                    leagueName.value = title
                                    expanded = false
                                    keyboardController?.hide()
                                    onSelectedLeague.invoke(title)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LeaguesItem(
    title: String,
    onSelect: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}



