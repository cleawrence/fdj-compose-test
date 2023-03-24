package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_team_detail

import com.appiwedia.apps.android.fdjcompose.common.SafeCall
import com.appiwedia.apps.android.fdjcompose.common.DispatcherProvider
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTeamDetailUseCase @Inject constructor(
    private val repository: LeagueRepository,
    private val dispatchers: DispatcherProvider,
) : SafeCall() {

    operator fun invoke(teamName: String): Flow<Resource<Team>> = flow {
        emit(safeApiCall(dispatchers.default) {
            repository.getTeamDetailByName(teamName).teams[0]
        })
    }
}