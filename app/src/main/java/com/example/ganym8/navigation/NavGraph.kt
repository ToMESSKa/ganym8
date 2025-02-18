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
import com.example.ganym8.ui.screens.AddExistingPartnerScreen
import com.example.ganym8.ui.screens.AddNewPartnerScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    sexActs: List<Encounter>,
    onAddSexAct: (Encounter) -> Unit
) {
    val partnerViewModel: PartnerViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(sexActs, navController)
        }

        composable(route = Screen.AddSexActForm.route) {
            AddNewEncounter(
                navController,
                partnerViewModel,
                onSave = { sexAct ->
                    onAddSexAct(sexAct)
                    navController.navigateUp()
                }
            )
        }
        composable(route = Screen.AddNewPartnerScreen.route) {
            AddNewPartnerScreen(navController, partnerViewModel)
        }
        composable(route = Screen.AddExistingPartnerScreen.route) {
            AddExistingPartnerScreen(navController, partnerViewModel)
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object AddSexActForm : Screen("add_sex_act_form")
    data object AddNewPartnerScreen : Screen("add_new_partner_screen")
    data object AddExistingPartnerScreen : Screen("add_existing_partner_screen") // New screen for adding a partner
// New screen for adding a partner
}
