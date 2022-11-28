package com.appiwedia.apps.android.fdjcompose.ui.team_league_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.domain.use_case.get_leagues.GetLeaguesUseCase
import com.appiwedia.apps.android.fdjcompose.domain.use_case.get_teams_ligue.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val getLeaguesUseCase: GetLeaguesUseCase,
) : ViewModel() {

    private val _leagueState = mutableStateOf(LeagueListState())
    val leagueState: State<LeagueListState> = _leagueState

    private val _teamState = mutableStateOf(TeamListState())
    val teamState: State<TeamListState> = _teamState

    init {
        getLeagues()
    }

    fun deleteAllTeams() {
        _teamState.value = TeamListState(teams = emptyList())
    }

    private fun getLeagues() {
        getLeaguesUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> _leagueState.value =
                    LeagueListState(error = result.message ?: "Une erreur inattendue est survenue")
                is Resource.Loading -> _leagueState.value = LeagueListState(isLoading = true)
                is Resource.Success -> _leagueState.value = LeagueListState(leagues = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun getTeamsFromLeague(strTeam: String) {
        getTeamsUseCase(strTeam).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _teamState.value =
                        TeamListState(error = result.message ?: "Une erreur inattendue est survenue")
                }
                is Resource.Loading -> _teamState.value = TeamListState(isLoading = true)
                is Resource.Success -> {
                    _teamState.value = TeamListState(teams = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}