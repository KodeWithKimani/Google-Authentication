package com.example.googleauth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googleauth.ui.theme.screens.home.HomeScreen
import com.example.googleauth.ui.theme.screens.signIn.SignInScreen
import com.example.googleauth.ui.theme.screens.splash.SplashScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination:String = ROUT_SPLASH) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ){

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_SIGNIN) {
            SignInScreen(navController)
        }

        composable(ROUT_HOME) {
            HomeScreen(navController)
        }



    }
}