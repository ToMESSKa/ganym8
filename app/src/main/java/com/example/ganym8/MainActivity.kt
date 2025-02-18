package com.example.ganym8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.ganym8.navigation.NavGraph
import com.example.ganym8.ui.theme.GanyM8Theme
import com.example.ganym8.models.Encounter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GanyM8Theme {
                val navController = rememberNavController()

                val sexActs = remember { mutableStateOf(listOf<Encounter>()) }
                NavGraph(
                    navController = navController, sexActs.value, onAddSexAct = { newAct ->
                        sexActs.value += newAct  // Correctly update state
                    }
                )
            }
        }
    }
}

