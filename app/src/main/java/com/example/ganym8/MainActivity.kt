package com.example.ganym8

import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.DateRange

import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.ganym8.ui.theme.GanyM8Theme
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.rememberDatePickerState


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
        Text(
            "Sex act list",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)  // <-- Added this line for top margin
        )
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
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Add a New Sex Act",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name input field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date selection field
        OutlinedTextField(
            value = date,
            onValueChange = {},
            label = { Text("Date") },
            readOnly = true,  // Prevent manual input
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Date"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Type input field
        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save button
        Button(
            onClick = {
                val newSexAct = SexAct(name, date, type)
                onSave(newSexAct)
                navController.navigateUp()
            }
        ) {
            Text("Save")
        }
    }

    // Show Date Picker Modal when needed
    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { selectedDateMillis ->
                if (selectedDateMillis != null) {
                    date = dateFormat.format(Date(selectedDateMillis))
                }
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
