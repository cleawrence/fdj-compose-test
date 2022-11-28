package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_leagues

import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.toLeague
import com.appiwedia.apps.android.fdjcompose.domain.models.League
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(
    private val repository: LeagueRepository,
) {
    operator fun invoke(): Flow<Resource<List<League>>> = flow {
        try {
            emit(Resource.Loading())
            val leagues = repository.getAllLeagues().leagues.map { it.toLeague() }
            emit(Resource.Success(leagues))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "une erreur inattendue est survenue"))
        } catch (e: IOException) {
            emit(Resource.Error("Nous n'avons pas pu atteindre le serveur. Vérifiez votre connexion internet"))
        }
    }
}