package com.example.ganym8.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ganym8.models.Activity
import com.example.ganym8.models.Encounter
import com.example.ganym8.models.Partner
import com.example.ganym8.ui.components.DatePickerModal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSexActScreen(navController: NavController, onSave: (Encounter) -> Unit) {
    var date by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val existingPartners = remember { listOf("Alice", "Bob", "Charlie") }
    var selectedPartners by remember { mutableStateOf(listOf<Partner>()) }

    // Partner input state
    var partners by remember { mutableStateOf(listOf<Partner>()) }

    // Activities input state
    var activities by remember { mutableStateOf(listOf<Activity>()) }

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Add a new encouter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


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

        // Partners input
        Text("Select Partners or Add New")

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedPartners.joinToString(", ") { it.name },  // Show selected partners as a comma-separated list
                onValueChange = { },
                label = { Text("Select Partner") },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select")
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                existingPartners.forEach { partnerName ->
                    DropdownMenuItem(
                        text = { Text(partnerName) }, // Text to display the partner name
                        onClick = {
                            // Add selected partner
                            selectedPartners = selectedPartners + Partner(
                                name = partnerName,
                                instagram = "aaa"
                            )
                            expanded = false  // Close dropdown after selection
                        },
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp), // Modifier for size and padding
                        leadingIcon = {
                            // Example icon before the partner name (can use your preferred icon)
                            Icon(Icons.Default.Info, contentDescription = "Partner Icon")
                        },
                        trailingIcon = {
                            // Example trailing icon (optional, can add one)
                            Icon(Icons.Default.MoreVert, contentDescription = "Selected Icon")
                        },
                        enabled = true,  // Optionally disable items based on conditions
                    )
                }
            }
        }
        


        // Save button
        Button(onClick = {
            val sexAct = Encounter(date, partners, activities)  // Use input state
            onSave(sexAct)  // Pass new entry back
        }) {
            Text("Save and Go Back")
        }

    }

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
    }}
