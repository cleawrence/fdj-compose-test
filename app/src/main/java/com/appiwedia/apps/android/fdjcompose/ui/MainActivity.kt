package com.appiwedia.apps.android.fdjcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appiwedia.apps.android.fdjcompose.ui.team_league_list.components.LeagueListScreen
import com.appiwedia.apps.android.fdjcompose.ui.theme.FdjComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FdjComposeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LeagueListScreen.route
                    ) {
                        composable(
                            route = Screen.LeagueListScreen.route
                        ) {
                            LeagueListScreen(navController)
                        }
                    }
                }
            }
        }
    }
}