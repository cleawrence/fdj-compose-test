package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_leagues

import com.appiwedia.apps.android.fdjcompose.common.BaseApiResponse
import com.appiwedia.apps.android.fdjcompose.common.DispatcherProvider
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.toLeague
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(
    private val repository: LeagueRepository,
    private val dispatchers: DispatcherProvider,
) : BaseApiResponse() {

    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        emit(safeApiCall(dispatchers.io) {
            repository.getAllLeagues().leagues.map { it.toLeague() }.map { it.strLeague }
        })
    }
}