package com.example.ganym8.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ganym8.ui.screens.MainScreen
import com.example.ganym8.ui.screens.AddSexActScreen
import com.example.ganym8.models.Encounter

@Composable
fun NavGraph(navController: NavHostController, sexActs: List<Encounter>, onAddSexAct: (Encounter) -> Unit) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(sexActs, navController)
        }

        composable(route = Screen.AddSexActForm.route) {
            AddSexActScreen(
                navController,
                onSave = { sexAct ->
                    onAddSexAct(sexAct)  // Update the list in MainActivity
                    navController.navigateUp()
                }
            )
        }
    }
}


sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object AddSexActForm : Screen("add_sex_act_form")
}
