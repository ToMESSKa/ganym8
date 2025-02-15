package com.example.ganym8.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

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
        /** 🔹 **Make Everything Scrollable (Except Save Button)** */
        Column(
            modifier = Modifier
                .weight(1f) // Ensures this section takes available space and allows scrolling
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()), // Enables scrolling for overflow
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /** 🔹 **Title** */
            Text(
                "Add a new encounter",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            /** 🔹 **Date Section** */
            Text(
                "date",
                style = MaterialTheme.typography.titleMedium, // Bolded title
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = date,
                onValueChange = {},
                label = { Text("Select Date") },
                readOnly = true,
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

            Spacer(modifier = Modifier.height(16.dp))

            /** 🔹 **Partners Section** */
            Text(
                "partners",
                style = MaterialTheme.typography.titleMedium, // Bolded title
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                verticalAlignment = Alignment.CenterVertically, // Center the content vertically
                horizontalArrangement = Arrangement.Start, // Align the button and text to the end (right)
                modifier = Modifier.fillMaxWidth() // Ensure the Row takes the full width
            ) {
                // Interaction source and indication to disable ripple effect
                val interactionSource = remember { MutableInteractionSource() }

                // IconButton without ripple effect
                IconButton(
                    onClick = { showModal = true },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.LightGray, CircleShape)
                        .clip(CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "add partners",
                    modifier = Modifier
                        .clickable(
                            indication = null, // Remove ripple effect on click
                            interactionSource = interactionSource // Same interaction source to handle click
                        ) {
                            showModal = true
                        }
                )
            }



            selectedPartners.forEach { selectedPartner ->
                PartnerCard(selectedPartner)
            }

            Spacer(modifier = Modifier.height(16.dp))

            /** 🔹 **Activities Section** */
            Text(
                "activities",
                style = MaterialTheme.typography.titleMedium, // Bolded title
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                verticalAlignment = Alignment.CenterVertically, // Center the content vertically
                horizontalArrangement = Arrangement.Start, // Align the button and text to the end (right)
                modifier = Modifier.fillMaxWidth() // Ensure the Row takes the full width
            ) {
                // Interaction source and indication to disable ripple effect
                val interactionSource = remember { MutableInteractionSource() }

                // IconButton without ripple effect
                IconButton(
                    onClick = { showActivitiesDialog = true },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.LightGray, CircleShape)
                        .clip(CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "add activities",
                    modifier = Modifier
                        .clickable(
                            indication = null, // Remove ripple effect on click
                            interactionSource = interactionSource // Same interaction source to handle click
                        ) {
                            showActivitiesDialog = true
                        }
                )
            }

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
        }

        /** 🔹 **Save Button (Always at Bottom, Outside Scrollable Area)** */
        Button(
            onClick = {
                val encounter = Encounter(date, selectedPartners, activities)
                onSave(encounter)
                navController.navigateUp()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp) // Keeps button always visible
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
    }
}
