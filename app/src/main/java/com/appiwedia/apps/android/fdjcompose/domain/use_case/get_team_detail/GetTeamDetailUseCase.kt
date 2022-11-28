package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_team_detail

import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.toTeam
import com.appiwedia.apps.android.fdjcompose.domain.models.Team
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTeamDetailUseCase @Inject constructor(
    private val repository: LeagueRepository
) {
    operator fun invoke(teamName: String) : Flow<Resource<Team>> = flow {
        try {
            emit(Resource.Loading())
            val team = repository.getTeamDetailByName(teamName)[0].toTeam()
            emit(Resource.Success(team))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Une erreur inattendue est survenue"))
        } catch (e: IOException) {
            emit(Resource.Error("Nous n'avons pas pu atteindre le serveur. Vérifiez votre connexion internet"))
        }
    }
}