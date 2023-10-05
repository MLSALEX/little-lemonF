package com.example.littlelemoncapstone

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(navController: NavHostController, database: AppDatabase) {

    val sharedPreferences =
        LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    val isLoggedIn = sharedPreferences.getBoolean("loggedIn", false)

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) {
            Home.route
        } else {
            Onboarding.route
        }
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController, database = database)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}
