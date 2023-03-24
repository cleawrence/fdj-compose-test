package com.appiwedia.apps.android.fdjcompose.di

import com.appiwedia.apps.android.fdjcompose.common.Constants
import com.appiwedia.apps.android.fdjcompose.common.DispatcherProvider
import com.appiwedia.apps.android.fdjcompose.domain.repository.LeagueRepository
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepositoryImpl
import com.appiwedia.apps.android.fdjcompose.data.service.LeagueServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTeamServiceApi(): LeagueServiceApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LeagueServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLeagueRepository(
        api: LeagueServiceApi,
    ): LeagueRepository = LeagueRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}