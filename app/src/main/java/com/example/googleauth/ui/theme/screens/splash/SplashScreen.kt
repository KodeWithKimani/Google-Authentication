package com.example.googleauth.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.googleauth.R
import com.example.googleauth.navigation.ROUT_SIGNIN
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googleauth.models.AuthViewModel
import com.example.googleauth.navigation.ROUT_HOME

@SuppressLint("CoroutineCreationDuringComposition")
@Composable

fun SplashScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
){


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val context = LocalContext.current


        LaunchedEffect(Unit) {
            delay(1200)

            if (authViewModel.currentUser != null) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(0)
                }
            } else {
                navController.navigate(ROUT_SIGNIN) {
                    popUpTo(0)
                }
            }
        }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE
        )



    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}