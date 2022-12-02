package com.appiwedia.apps.android.fdjcompose.domain.use_case.get_leagues

import com.appiwedia.apps.android.fdjcompose.CoroutineTestRule
import com.appiwedia.apps.android.fdjcompose.common.Resource
import com.appiwedia.apps.android.fdjcompose.data.repository.LeagueRepositoryImpl
import com.appiwedia.apps.android.fdjcompose.data.service.LeagueServiceApi
import com.appiwedia.apps.android.fdjcompose.domain.mapper.LeaguesDomainMapper
import com.appiwedia.apps.android.fdjcompose.domain.use_case.data.FakeLeaguesData
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
class GetLeaguesUseCaseTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var leagueRepository: LeagueRepositoryImpl
    private lateinit var mapper: LeaguesDomainMapper
    private lateinit var api: LeagueServiceApi
    private val client = OkHttpClient.Builder().build()

    private val getLeaguesUseCase by lazy {
        GetLeaguesUseCase(leagueRepository, coroutinesTestRule.testDispatcherProvider, mapper)
    }

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        mapper = LeaguesDomainMapper()
        val moshiConverterFactory = MoshiConverterFactory.create()
        mockWebServer = MockWebServer()

        api = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .client(client).addConverterFactory(moshiConverterFactory.asLenient()).build()
            .create(LeagueServiceApi::class.java)

        leagueRepository = LeagueRepositoryImpl(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check if 400 is thrown`() = runTest {
        val response = MockResponse()
            .setBody("The client messed this up")
            .setResponseCode(400)

        mockWebServer.enqueue(response)

        val result = getLeaguesUseCase.invoke().single()
        assert(result is Resource.Error)
        assert(result.message == ("Http Exception: HTTP 400 Client Error"))
    }

    @Test
    fun `check if exception is thrown for malformed JSON`() = runTest {
        val response = MockResponse().setBody("Malformed JSON").setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = getLeaguesUseCase.invoke()
        launch {
            flow.collect {
                assert(it is Resource.Error)
                assert(it.message?.contains("Expected BEGIN_OBJECT but was STRING at path $") == true)
            }
        }
    }

    @Test
    fun `check if 500 is thrown`() = runTest {
        val response = MockResponse()
            .setBody("Server messed this up!")
            .setResponseCode(500)

        mockWebServer.enqueue(response)

        val flow = getLeaguesUseCase.invoke()
        launch {
            flow.collect {
                assert((it as Resource.Error).message.equals("Http Exception: HTTP 500 Server Error"))
            }
        }
    }

    @Test
    fun `check successful response for get all leagues`() = runTest {
        val fakeResponse = FakeLeaguesData.build()

        val response = MockResponse()
            .setBody(fakeResponse.first)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = getLeaguesUseCase.invoke()

        launch {
            flow.collect {
                assert(it is Resource.Success)
                assert((it as Resource.Success).data?.get(0)?.strLeague == fakeResponse.second.leagues[0].strLeague)
                assert(it.data?.get(0)?.idLeague == fakeResponse.second.leagues[0].idLeague)
                assert(it.data?.get(0)?.strSport == fakeResponse.second.leagues[0].strSport)
                assert(it.data?.get(0)?.strLeagueAlternate == fakeResponse.second.leagues[0].strLeagueAlternate)
            }
        }
    }
}