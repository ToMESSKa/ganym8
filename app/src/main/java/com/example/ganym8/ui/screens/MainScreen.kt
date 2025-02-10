package com.example.ganym8.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ganym8.models.Encounter
import com.example.ganym8.navigation.Screen
import com.example.ganym8.ui.components.SexActItem

@Composable
fun MainScreen(sexActs: List<Encounter>, navController: NavController) {
    // A mutable list of SexAct objects (stateful)

    Scaffold(
        floatingActionButton = {
            // Floating action button to navigate to AddSexActForm
            FloatingActionButton(
                onClick = {
                    // Navigate to the Add Sex Act Form screen
                    navController.navigate(Screen.AddSexActForm.route)
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add New Sex Act")
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            // Title
            Text(
                text = "Sex Acts List",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(16.dp)
            )

            // List of Sex Acts
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(sexActs) { sexAct ->
                    SexActItem(sexAct)  // Use the existing composable
                }
            }
        }
        }
    }


