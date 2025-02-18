package com.example.ganym8.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import com.example.ganym8.models.Partner


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExistingPartnerDialog(
    onAddPartnerDialogShownChange: (Boolean) -> Unit,
    onAddExistingPartnerDialogShownChange: (Boolean) -> Unit,
    newName: String,
    onNewNameChange: (String) -> Unit,
    newInstagram: String,
    onNewInstagramChange: (String) -> Unit,
    selectedPartners: List<Partner>,
    selectedPartner: Partner,
    onSelectedPartnersChange: (List<Partner>) -> Unit,
    onSelectedPartnerChange: (Partner) -> Unit,
    onDropDownExpandedChange: (Boolean) -> Unit,
    expanded: Boolean, existingPartners: List<Partner>,
    newPhoneNumber: String,
    onNewPhoneNumberChange: (String) -> Unit,
    newNote: String,
    onNewNoteChange: (String) -> Unit,
    newOccupation: String,
    onNewOccupationChange: (String) -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onAddExistingPartnerDialogShownChange(false)
        },
        confirmButton = {
            Button(onClick = {
                val newPartner =
                    Partner(
                        name = selectedPartner.name, instagram = selectedPartner.instagram,
                        occupation = selectedPartner.occupation,
                        phoneNumber = selectedPartner.phoneNumber,
                        note = selectedPartner.note
                    )
                onSelectedPartnersChange(selectedPartners + newPartner)
                onAddExistingPartnerDialogShownChange(false)
                onAddPartnerDialogShownChange(false)
                onNewNameChange("")
                onNewInstagramChange("")
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = {
                onAddExistingPartnerDialogShownChange(false)
                onAddPartnerDialogShownChange(false)
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
                    onExpandedChange = { onDropDownExpandedChange(!expanded)}
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
                        onDismissRequest = {onDropDownExpandedChange(false)}
                    ) {
                        existingPartners.forEach { existingPartner ->
                            DropdownMenuItem(
                                text = { Text(existingPartner.name) },
                                onClick = {
                                    onSelectedPartnerChange(existingPartner)
                                    onDropDownExpandedChange(false)
                                }
                            )
                        }
                    }
                }

            }
        }
    )
}

