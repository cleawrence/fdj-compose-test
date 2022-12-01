package com.appiwedia.apps.android.fdjcompose.ui.team_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appiwedia.apps.android.fdjcompose.common.Constants
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.domain.use_case.get_team_detail.GetTeamDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _teamDetailState = mutableStateOf(TeamDetailState())
    val teamDetailState: State<TeamDetailState> = _teamDetailState

    init {
        savedStateHandle.get<String>(Constants.PARAM_TEAM_NAME)?.let { teamName ->
            getTeamDetail(teamName)
        }
    }

    private fun getTeamDetail(teamName: String) {
        getTeamDetailUseCase(teamName).onEach { result ->
            when (result) {
                is Resource.Error -> _teamDetailState.value = TeamDetailState(error = result.message ?: "")
                is Resource.Loading -> _teamDetailState.value = TeamDetailState(isLoading = true)
                is Resource.Success -> _teamDetailState.value = TeamDetailState(teamDetail = result.data)
            }
        }.launchIn(viewModelScope)
    }
}