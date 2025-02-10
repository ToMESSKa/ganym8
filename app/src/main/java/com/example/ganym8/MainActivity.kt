package com.example.ganym8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.ganym8.ui.theme.GanyM8Theme

data class SexAct(val name: String, val date: String, val type: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GanyM8Theme {
                val navController = rememberNavController()
                var sexActs by remember { mutableStateOf(listOf<SexAct>()) }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("form") },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add Note")
                        }
                    })
                { innerPadding ->
                    // New Code: Navigation Host to switch between screens
                    NavHost(navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(sexActs, navController)  // Pass the sex acts to MainScreen
                        }
                        composable("form") {
                            AddSexActForm(navController, onSave = { newSexAct ->
                                // New Code: Update the sex acts list when a new sex act is saved
                                sexActs = sexActs + newSexAct
                                navController.navigateUp()  // Navigate back to main screen
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(sexActs: List<SexAct>, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Sex Acts List", style = MaterialTheme.typography.headlineLarge)

        // New Code: LazyColumn to display the list of sex acts
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sexActs) { sexAct ->
                Text("${sexAct.name} - ${sexAct.date} - ${sexAct.type}")
            }
        }
    }
}

@Composable
fun AddSexActForm(navController: NavController, onSave: (SexAct) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var date by remember { mutableStateOf(TextFieldValue()) }
    var type by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Add a New Sex Act", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Name input field
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date input field
        BasicTextField(
            value = date,
            onValueChange = { date = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Type input field
        BasicTextField(
            value = type,
            onValueChange = { type = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save button
        Button(
            onClick = {
                val newSexAct = SexAct(name.text, date.text, type.text)
                onSave(newSexAct)  // Pass the new sex act to the parent to update the state
            }
        ) {
            Text("Save")
        }
    }
}