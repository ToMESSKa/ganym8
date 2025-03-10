package com.example.ganym8.ui.screens

import PartnerViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
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
import com.example.ganym8.ui.components.PartnerCard
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.ganym8.models.ActivityType
import com.example.ganym8.navigation.Screen
import com.example.ganym8.ui.components.AddItemButton
import com.example.ganym8.ui.components.AddPartnerDialog
import com.example.ganym8.ui.components.DatePicker
import com.example.ganym8.ui.components.LocationPicker
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewEncounter(navController: NavController, partnerViewModel: PartnerViewModel, onSave: (Encounter) -> Unit) {
    var date by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("Budapest") }
    var isDatePickerDialogShown by remember { mutableStateOf(false) }
    var isAddPartnerDialogShown by remember { mutableStateOf(false) }
    var isAddExistingPartnerDialogShown by remember { mutableStateOf(false) }
    var isAddNewPartnerDialogShown by remember { mutableStateOf(false) }
    var showActivitiesDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var onAddPartnerDialogShownChange by remember { mutableStateOf(false) }
    var selectedPartners = partnerViewModel.getPartners()

    val existingPartners = remember {
        listOf(
            Partner(
                name = "Ömer", instagram = "meryn", occupation = "",
                phoneNumber = "+36309469120",
                note = "",
                lastEncounter = LocalDate.now()
            ),
            Partner(
                name = "Alexander", instagram = "alexr",
                occupation = "",
                phoneNumber = "",
                note = "",
                lastEncounter = LocalDate.now()
            ),
            Partner(
                name = "Balázs", instagram = "balazs69",
                occupation = "",
                phoneNumber = "",
                note = "",
                lastEncounter = LocalDate.now()
            )
        )
    }

    val activities = remember {
        listOf(
            ActivityType(name = "blowjob"),
            ActivityType(name = "rimming"),
            ActivityType(name = "kissing"),
            ActivityType(name = "fucking"),
            ActivityType(name = "nipple-play")
        )
    }

    val selectedActivities = remember { mutableStateOf(mutableListOf<Activity>()) }

    var newName by remember { mutableStateOf("") }
    var newInstagram by remember { mutableStateOf("") }
    var newOccupation by remember { mutableStateOf("") }
    var newPhoneNumber by remember { mutableStateOf("") }
    var newNote by remember { mutableStateOf("") }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f) // Ensures this section takes available space and allows scrolling
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()), // Enables scrolling for overflow
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Add a new encounter",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            /** 🔹 **Date Section** */
            Text(
                "date",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            DatePicker(
                isDatePickerDialogShown = isDatePickerDialogShown,
                date = date,
                onDateChange = { selectedDate ->
                    date = selectedDate
                },
                onDatePickerDialogShownChange = { newState ->
                    isDatePickerDialogShown = newState
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            /** 🔹 **Location Section** */
            Text(
                "location",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LocationPicker(location = location)

            Spacer(modifier = Modifier.height(16.dp))

            /** 🔹 **Partners Section** */
            Text(
                "partners",
                style = MaterialTheme.typography.titleMedium, // Bolded title
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            AddItemButton(
                onClick = { isAddPartnerDialogShown = true },
                buttonText = "add partners"
            )

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

            AddItemButton(
                onClick = { navController.navigate(Screen.AddActivityScreen.route)},
                buttonText = "add activities"
            )

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
    
    if (isAddPartnerDialogShown) {
        AddPartnerDialog(
            onAddPartnerDialogShownChange = { isShown ->
                isAddPartnerDialogShown = isShown},
            onAddExistingPartnerDialogShownChange = { isShown ->
                isAddExistingPartnerDialogShown = isShown},
            onAddNewPartnerDialogShownChange = { isShown ->
                isAddNewPartnerDialogShown = isShown},
            navController= navController
        )

    }

//    if (isAddNewPartnerDialogShown) {
//        AddNewPartnerDialog(
//            onAddPartnerDialogShownChange = { isShown -> isAddPartnerDialogShown = isShown },
//            onAddNewPartnerDialogShownChange = { isShown ->
//                isAddNewPartnerDialogShown = isShown
//            },
//            newName = newName,
//            onNewNameChange = { name ->
//                newName = name
//            },
//            newInstagram = newInstagram,
//            onNewInstagramChange = { instagram ->
//                newInstagram = instagram
//            },
//            selectedPartners = "",
//            onSelectedPartnersChange = { partners ->
//                selectedPartners = partners
//            },
//            newPhoneNumber = newPhoneNumber,
//            onNewPhoneNumberChange = { phoneNumber ->
//                newPhoneNumber = phoneNumber
//            },
//            newNote = newNote,
//            onNewNoteChange = { note ->
//                newNote = note
//            },
//            newOccupation = newOccupation,
//            onNewOccupationChange = { occupation ->
//                newOccupation = occupation
//            },
//        )
//    }


//
//    if (isAddExistingPartnerDialogShown) {
//        AddExistingPartnerDialog(
//            onAddPartnerDialogShownChange = { isShown -> isAddPartnerDialogShown = isShown },
//            onAddExistingPartnerDialogShownChange = { isShown -> isAddExistingPartnerDialogShown = isShown },
//            newName = newName,
//            onNewNameChange = { name ->
//                newName = name
//            },
//            newInstagram = newInstagram,
//            onNewInstagramChange = { instagram ->
//                newInstagram = instagram
//            },
//            selectedPartners = selectedPartners,
//            selectedPartner = selectedPartner,
//            onSelectedPartnersChange = {partners ->
//                selectedPartners = partners
//            },
//            onSelectedPartnerChange = {partner ->
//                selectedPartner = partner
//            },
//            onDropDownExpandedChange = {isShown ->
//                expanded = isShown
//            },
//            expanded = expanded,
//            existingPartners = existingPartners,
//            newPhoneNumber = newPhoneNumber,
//            onNewPhoneNumberChange = { phoneNumber ->
//                newPhoneNumber = phoneNumber
//            },
//            newNote = newNote,
//            onNewNoteChange = { note ->
//                newNote = note
//            },
//            newOccupation = newOccupation,
//            onNewOccupationChange = { occupation ->
//                newOccupation = occupation
//            },
//        )
//
//    }

//    if (showActivitiesDialog) {
//        ActivitiesDialog(
//            activities = activities,
//            showActivitiesDialog = true,
//            selectedActivities = selectedActivities.value, // Pass the list from parent
//            onUpdateSelectedActivities = { updatedActivities ->
//                // Directly update selectedActivities in the parent
//                selectedActivities.value = updatedActivities.toMutableList()
//            },
//            onDismissDialog = { showActivitiesDialog = false })
//    }
}
