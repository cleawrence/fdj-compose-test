package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_team_detail

import com.appiwedia.apps.android.fdjcompose.common.BaseApiResponse
import com.appiwedia.apps.android.fdjcompose.common.DispatcherProvider
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepository
import com.appiwedia.apps.android.fdjcompose.data.mapper.TeamDomainMapper
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTeamDetailUseCase @Inject constructor(
    private val repository: LeagueRepository,
    private val dispatchers: DispatcherProvider,
    private val mapper: TeamDomainMapper
) : BaseApiResponse() {

    operator fun invoke(teamName: String): Flow<Resource<Team>> = flow {
        emit(safeApiCall(dispatchers.default) {
            mapper.toDomain(repository.getTeamDetailByName(teamName)).teams[0]
        })
    }
}