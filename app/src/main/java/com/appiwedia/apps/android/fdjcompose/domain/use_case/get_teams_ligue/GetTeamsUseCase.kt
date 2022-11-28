package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_teams_ligue

import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.toTeam
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val repository: LeagueRepository,
) {
    operator fun invoke(strTeam: String): Flow<Resource<List<Team>>> = flow {
        try {
            emit(Resource.Loading())
            val teams = repository.getAllTeams(strTeam).teams.map { it.toTeam() }
            emit(Resource.Success(teams))
        } catch (e: IOException) {
            emit(Resource.Error("IO Exception: ${e.message}"))
        } catch (e: TimeoutException) {
            emit(Resource.Error("Timeout Exception: ${e.message}"))
        } catch (e: HttpException) {
            emit(Resource.Error("Http Exception: ${e.message}"))
        }
    }
}