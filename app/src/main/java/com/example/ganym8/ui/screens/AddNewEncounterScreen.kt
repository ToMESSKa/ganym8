package com.example.ganym8.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ganym8.models.Activity
import com.example.ganym8.models.Encounter
import com.example.ganym8.models.Partner
import com.example.ganym8.ui.components.ActivitiesDialog
import com.example.ganym8.ui.components.DatePickerModal
import com.example.ganym8.ui.components.PartnerCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewEncounterScreen(navController: NavController, onSave: (Encounter) -> Unit) {
    var date by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showModal by remember { mutableStateOf(false) }
    var showAddNewPartnerModal by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }
    var showActivitiesDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Option 1", "Option 2", "Option 3")


    // Placeholder data for existing partners
    val existingPartners = remember {
        listOf(
            Partner(name = "Ömer", instagram = "meryn"),
            Partner(name = "Alexander", instagram = "alexr"),
            Partner(name = "Balázs", instagram = "balazs69")
        )
    }
    var selectedPartners by remember { mutableStateOf(listOf<Partner>()) }
    var selectedPartner by remember { mutableStateOf(existingPartners.first()) }

    val activities = remember {
        listOf(
            Activity(name = "blowjob"),
            Activity(name = "rimming"),
            Activity(name = "kissing"),
            Activity(name = "fucking"),
            Activity(name = "nipple-play")
        )
    }

    val selectedActivities = remember { mutableStateOf(mutableListOf<Activity>()) }


    // Partner and activity states
    var partners by remember { mutableStateOf(listOf<Partner>()) }
    var newName by remember { mutableStateOf("") }
    var newInstagram by remember { mutableStateOf("") }

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Add a new encounter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = date,
            onValueChange = {},
            label = { Text("Date") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select Date")
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Select Partners or Add New")

        Button(
            onClick = { showModal = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier = Modifier.width(8.dp))
            Text("add partners")
        }

        // Use weight(1f) so the LazyColumn takes available space but does not push the button out
        LazyColumn(
            modifier = Modifier
                .weight(1f)  // <-- This makes the list take available space but leaves room for the button
                .fillMaxWidth()
        ) {
            items(selectedPartners) { selectedPartner ->
                PartnerCard(selectedPartner)
            }
        }

        Button(
            onClick = { showActivitiesDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier = Modifier.width(8.dp))
            Text("add activities")
        }

        Column(modifier = Modifier.padding(16.dp)) {
            selectedActivities.value.forEach { activity ->
                Button(
                    onClick = { },
                    modifier = Modifier.width(200.dp)
                ) {
                    Icon(Icons.Default.ThumbUp, contentDescription = "Add")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(activity.name)
                }
            }




        // Save Button (Always at Bottom)
        Button(
            onClick = {
                val encounter = Encounter(date, selectedPartners, activities)
                onSave(encounter)
                navController.navigateUp()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)  // Added padding for better visibility
        ) {
            Text("Save and Go Back")
        }
    }


    // Date Picker Modal
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

    // Partner Selection Modal (placed outside Column to avoid layout issues)
    if (showModal) {
        AlertDialog(
            onDismissRequest = { showModal = false },
            confirmButton = { },
            dismissButton = {
                Button(onClick = { showModal = false }) {
                    Text("cancel")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { showAddNewPartnerModal = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)  // Adds space between the Icon and Text
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add New Partner")
                            Text("add new partner", textAlign = TextAlign.Center)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Add Old Partner Button
                    Button(
                        onClick = { showDropdown = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)  // Adds space between the Icon and Text
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add New Partner")
                            Text("select existing partner", textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        )
    }

    if (showAddNewPartnerModal) {
        AlertDialog(
            onDismissRequest = {
                showModal = false
                showAddNewPartnerModal = false
            },
            confirmButton = {
                Button(onClick = {
                    // Perform save logic here if needed
                    // Create a new Partner object and add it to selectedPartners
                    val newPartner = Partner(name = newName, instagram = newInstagram)
                    selectedPartners = selectedPartners + newPartner  // Add to the list
                    showModal = false
                    showAddNewPartnerModal = false
                    newName = ""
                    newInstagram = ""
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showModal = false
                    showAddNewPartnerModal = false
                }) {
                    Text("Cancel")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newText -> newName = newText },  // Update the value here
                        label = { Text("name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = newInstagram,
                        onValueChange = { newText ->
                            newInstagram = newText
                        },  // Update the value here
                        label = { Text("instagram") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        )
    }

    if (showDropdown) {
        AlertDialog(
            onDismissRequest = {
                showModal = false
                showDropdown = false
            },
            confirmButton = {
                Button(onClick = {
                    val newPartner =
                        Partner(name = selectedPartner.name, instagram = selectedPartner.instagram)
                    selectedPartners = selectedPartners + newPartner  // Add to the list
                    showModal = false
                    showDropdown = false
                    newName = ""
                    newInstagram = ""
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showModal = false
                    showDropdown = false
                }) {
                    Text("Cancel")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedPartner.name,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(),
                            label = { Text("Select an option") },
                            trailingIcon = {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            existingPartners.forEach { existingPartner ->
                                DropdownMenuItem(
                                    text = { Text(existingPartner.name) },
                                    onClick = {
                                        selectedPartner = existingPartner
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                }
            }
        )

    }

    if (showActivitiesDialog) {
        ActivitiesDialog(
            activities = activities,
            showActivitiesDialog = true,
            selectedActivities = selectedActivities.value, // Pass the list from parent
            onUpdateSelectedActivities = { updatedActivities ->
                // Directly update selectedActivities in the parent
                selectedActivities.value = updatedActivities.toMutableList()
            },
            onDismissDialog = { showActivitiesDialog = false })
    }}}
