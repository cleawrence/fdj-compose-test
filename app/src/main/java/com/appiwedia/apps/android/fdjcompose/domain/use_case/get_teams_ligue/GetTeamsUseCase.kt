package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_teams_ligue

import com.appiwedia.apps.android.fdjcompose.common.BaseApiResponse
import com.appiwedia.apps.android.fdjcompose.common.DispatcherProvider
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.toTeam
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val repository: LeagueRepository,
    private val dispatchers: DispatcherProvider,
) : BaseApiResponse() {

    operator fun invoke(strTeam: String): Flow<Resource<List<Team>?>> = flow {
        emit(safeApiCall(dispatchers.io) {
            filterTeamsByDescendingWithOneTimeByTwo(repository.getAllTeams(strTeam).teams.map { it.toTeam() })
        })
    }

    private fun filterTeamsByDescendingWithOneTimeByTwo(data: List<Team>?): List<Team>? {
        return data?.sortedByDescending { it.strTeam }?.filterIndexed { index, _ ->
            index % 2 == 0
        }
    }
}