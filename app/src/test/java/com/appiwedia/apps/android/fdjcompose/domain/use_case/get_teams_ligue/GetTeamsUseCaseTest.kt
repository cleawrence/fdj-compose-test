package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_teams_ligue

import com.appiwedia.apps.android.fdjcompose.CoroutineTestRule
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepositoryImpl
import com.appiwedia.apps.android.fdjcompose.data.service.LeagueServiceApi
import com.appiwedia.apps.android.fdjcompose.data.mapper.TeamDomainMapper
import com.appiwedia.apps.android.fdjcompose.domain.use_case.data.FakeTeams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class GetTeamsUseCaseTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: LeagueServiceApi
    private val client = OkHttpClient.Builder().build()
    private lateinit var leagueRepository: LeagueRepositoryImpl
    private lateinit var mapper: TeamDomainMapper

    private val getTeamsUseCase by lazy {
        GetTeamsUseCase(leagueRepository, coroutinesTestRule.testDispatcherProvider, mapper)
    }

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        val moshiConverterFactoryFactory = MoshiConverterFactory.create()
        mockWebServer = MockWebServer()
        mapper = TeamDomainMapper()

        api = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .client(client).addConverterFactory(moshiConverterFactoryFactory.asLenient())
            .build().create(LeagueServiceApi::class.java)

        leagueRepository = LeagueRepositoryImpl(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testTeamsUseCase_Malformed_Json() = runTest {
        val response = MockResponse().setBody("Malformed JSON").setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = getTeamsUseCase.invoke("")
        launch {
            flow.collect {
                assert(it is Resource.Error)
                assert((it as Resource.Error).message?.contains("Expected BEGIN_OBJECT but was STRING at path $") == true)
            }
        }
    }

    @Test
    fun check_if_400_is_thrown_get_teams() = runTest {
        val response = MockResponse()
            .setBody("The client messed this up")
            .setResponseCode(400)

        mockWebServer.enqueue(response)

        val result = getTeamsUseCase.invoke("").single()
        assert(result is Resource.Error)
        assert((result as Resource.Error).message.equals("Http Exception: HTTP 400 Client Error"))
    }

    @Test
    fun check_if_500_is_thrown_get_teams() = runTest {
        val response = MockResponse()
            .setBody("Server messed this up!")
            .setResponseCode(500)

        mockWebServer.enqueue(response)

        val flow = getTeamsUseCase.invoke("")

        launch {
            flow.collect {
                assert((it as Resource.Error).message.equals("Http Exception: HTTP 500 Server Error"))
            }
        }
    }

    @Test
    fun check_if_teams_are_successfully_retrieved() = runTest {
        val fakeResponse = FakeTeams.build()

        val response = MockResponse()
            .setBody(fakeResponse.first)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = getTeamsUseCase.invoke("")

        launch {
            flow.collect {
                println(it.data)
                assert(it is Resource.Success)
                assert((it as Resource.Success).data?.get(0)?.strLeague == fakeResponse.second.teams[0].strLeague)
            }
        }
    }

    @Test
    fun test_filterByDescendingWith1OutOf2_teams() = runTest {
        val fakeResponse = mapper.toDomain(FakeTeams.build().second).teams
        val teamsDescendingOrderWith1OutOf2 =
            getTeamsUseCase.filterTeamsByDescendingWithOneTimeByTwo(fakeResponse)

        val sizeNormal = fakeResponse.size
        val sizeTeamsFiltered = teamsDescendingOrderWith1OutOf2?.size ?: 1

        assert(fakeResponse.last().strTeam == teamsDescendingOrderWith1OutOf2?.first()?.strTeam)
        assert((sizeTeamsFiltered % 2 == 0))
        assert(sizeTeamsFiltered.div(sizeNormal) == 0)
    }
}