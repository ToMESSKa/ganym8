package com.example.ganym8.navigation

import PartnerViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ganym8.ui.screens.MainScreen
import com.example.ganym8.ui.screens.AddNewEncounter
import com.example.ganym8.models.Encounter
import com.example.ganym8.ui.components.AddNewPartnerDialog


@Composable
fun NavGraph(
    navController: NavHostController,
    sexActs: List<Encounter>,
    onAddSexAct: (Encounter) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(sexActs, navController)
        }

        composable(route = Screen.AddSexActForm.route) {
            AddNewEncounter(
                navController,
                onSave = { sexAct ->
                    onAddSexAct(sexAct)
                    navController.navigateUp()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object AddSexActForm : Screen("add_sex_act_form") }
