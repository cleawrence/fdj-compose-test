package com.appiwedia.apps.android.fdjcompose.data.repository

import com.appiwedia.apps.android.fdjcompose.data.mapper.toLeagues
import com.appiwedia.apps.android.fdjcompose.data.mapper.toTeams
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.league.LeaguesResponse
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamDto
import com.appiwedia.apps.android.fdjcompose.data.remote.dto.team.TeamsResponse
import com.appiwedia.apps.android.fdjcompose.data.service.LeagueServiceApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito


@OptIn(ExperimentalCoroutinesApi::class)
class LeagueRepositoryImplTest {
    private val api = Mockito.mock(LeagueServiceApi::class.java)
    private val sut = LeagueRepositoryImpl(api)

    @Test
    fun verify_correct_leagues_is_retrieved() = runTest {
        val leaguesResponse = LeaguesResponse(emptyList()).toLeagues()
        Mockito.`when`(api.getAllLeagues()).then { LeaguesResponse(emptyList()) }

        val leagues = sut.getAllLeagues()

        assert(leagues == leaguesResponse)
    }

    @Test
    fun verify_correct_emptylist_teams_are_retrieved() = runTest {
        val teamsResponse = TeamsResponse(emptyList()).toTeams()
        Mockito.`when`(api.getAllTeams("")).then { TeamsResponse(emptyList()) }
        val teams = sut.getAllTeams("")
        assert(teamsResponse == teams)
    }

    @Test
    fun verify_correct_team_detail_ajaccacio_are_retrieved() = runTest {
        val team = TeamDto(
            idTeam = "133702",
            idSoccerXML = "117",
            idAPIfootball = "98",
            intLoved = null,
            strTeam = "Ajaccio",
            strTeamShort = null,
            strAlternate = "AC Ajaccio, Athletic Club Aiacciu",
            intFormedYear = "1910",
            strSport = "Soccer",
            strLeague = "French Ligue 1",
            idLeague = "4334",
            strLeague2 = "Coupe de France",
            idLeague2 = "4484",
            strLeague3 = "",
            idLeague3 = null,
            strLeague4 = "",
            idLeague4 = null,
            strLeague5 = "",
            idLeague5 = null,
            strLeague6 = "",
            idLeague6 = null,
            strLeague7 = "",
            idLeague7 = null,
            strDivision = null,
            strManager = "",
            strStadium = "Stade François Coty",
            strKeywords = "L'ACA",
            strRSS = "",
            strStadiumThumb = "https://www.thesportsdb.com/images/media/team/stadium/tpspsp1420759508.jpg",
            strStadiumDescription = "Stade François Coty is a football stadium in the Corsican city of Ajaccio, France, and the home of AC Ajaccio. Its capacity is 10,660.\r\n\r\nThe stadium was inaugurated on 1 December 1969 under the name Parc des Sports de l'ACA. A crowd of 14,421 was in attendance to see AC Ajaccio defeat SC Bastia in the Corsican derby. Known informally as Timizzolu, the stadium was renovated in 2002 and renamed after François Coty, a businessman and far-right politician from Ajaccio. Since 2007, the stadium has undergone substantial improvements to enable it to host Ligue 1 matches.",
            strStadiumLocation = "Ajaccio",
            intStadiumCapacity = "10500",
            strWebsite = "ac-ajaccio.corsica",
            strFacebook = "www.facebook.com/ACAofficiel/",
            strTwitter = "twitter.com/ACAjaccio",
            strInstagram = "www.instagram.com/acajaccio",
            strDescriptionEN = "Athletic Club Ajaccio (Corsican: Athletic Club Aiacciu), commonly referred to as AC Ajaccio, ACA or simply Ajaccio, is a French association football club based in the city of Ajaccio on the island of Corsica. The club was founded in 1910 and plays in Ligue 1. The club president is Christian Leca, and the first-team is coached by manager Olivier Pantaloni, following the sacking of Christian Bracconi in October 2014. Ajaccio play their home matches at the Stade François Coty and are rivals with fellow Corsican club Bastia, with whom they contest the Corsica derby (Derby Corse).",
            strDescriptionDE = null,
            strDescriptionFR = "L'Athletic Club Ajaccio (dont le nom officiel en corse est Athletic Club Aiacciu), couramment abrégé en AC Ajaccio ou ACA, est un club de football français fondé en 1910. Il est présidé par Alain Orsoni depuis juillet 2008. L'équipe première évolue en Ligue 2 depuis la saison 2014-2015. L'AC Ajaccio a remporté deux titres de champion de France de Ligue 2 en 1967 et 2002 et un titre de champion de France amateur (National) en 1998. Il dispute ses matches à domicile au Stade François Coty.",
            strDescriptionCN = null,
            strDescriptionIT = null,
            strDescriptionJP = null,
            strDescriptionRU = null,
            strDescriptionES = "El Athletic Club Ajaccien (conocido como AC Ajaccio) es un club de fútbol francés, de la ciudad de Ajaccio en la isla de Córcega. Fue fundado en 1910 y juega en la Ligue 1 del fútbol francés. El club mantiene una tradicional rivalidad con el SC Bastia, también de la isla, con el que disputa el \"Derbi de Córcega\".",
            strDescriptionPT = null,
            strDescriptionSE = null,
            strDescriptionNL = null,
            strDescriptionHU = null,
            strDescriptionNO = null,
            strDescriptionIL = null,
            strDescriptionPL = null,
            strKitColour1 = "#ED2228",
            strKitColour2 = "#FFFFFF",
            strKitColour3 = "",
            strGender = "Male",
            strCountry = "France",
            strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/qpxvwy1473505505.png",
            strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/rk1n921616146043.png",
            strTeamLogo = "https://www.thesportsdb.com/images/media/team/logo/twttqt1420759362.png",
            strTeamFanart1 = "https://www.thesportsdb.com/images/media/team/fanart/vsvwpy1420758594.jpg",
            strTeamFanart2 = "https://www.thesportsdb.com/images/media/team/fanart/txxrpq1420758610.jpg",
            strTeamFanart3 = "https://www.thesportsdb.com/images/media/team/fanart/tqvuqv1420758626.jpg",
            strTeamFanart4 = "https://www.thesportsdb.com/images/media/team/fanart/sqrsvu1420759017.jpg",
            strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/upwrpx1420758932.jpg",
            strYoutube = "www.youtube.com/user/ACAiacciuofficiel",
            strLocked = "unlocked"
        )

        val listTeamDto = listOf(team)
        val teamDetailResponse = TeamsResponse(listTeamDto).toTeams()

        Mockito.`when`(api.getTeamDetailByName("Ajaccio")).then { TeamsResponse(listTeamDto) }

        val teamDetail = sut.getTeamDetailByName("Ajaccio")
        assert(teamDetailResponse.teams[0] == teamDetail.teams[0])
    }
}
