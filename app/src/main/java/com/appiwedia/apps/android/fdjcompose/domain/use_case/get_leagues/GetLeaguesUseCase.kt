package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_leagues

import com.appiwedia.apps.android.fdjcompose.common.BaseApiResponse
import com.appiwedia.apps.android.fdjcompose.common.DispatcherProvider
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepository
import com.appiwedia.apps.android.fdjcompose.domain.mapper.LeaguesDomainMapper
import com.appiwedia.apps.android.fdjcompose.domain.models.League
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(
    private val repository: LeagueRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val mapper: LeaguesDomainMapper
) : BaseApiResponse() {

    operator fun invoke(): Flow<Resource<List<League>>> = flow {
        emit(safeApiCall(dispatcherProvider.io) { mapper.toDomain(repository.getAllLeagues()).leagesUi })
    }
}