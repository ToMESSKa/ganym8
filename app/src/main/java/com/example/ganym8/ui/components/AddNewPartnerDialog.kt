package com.example.ganym8.ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.ganym8.models.Partner


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPartnerDialog(
    onAddPartnerDialogShownChange: (Boolean) -> Unit,
    onAddNewPartnerDialogShownChange: (Boolean) -> Unit,
    newName: String,
    onNewNameChange: (String) -> Unit,
    newInstagram: String,
    onNewInstagramChange: (String) -> Unit,
    newPhoneNumber: String,
    onNewPhoneNumberChange: (String) -> Unit,
    newNote: String,
    onNewNoteChange: (String) -> Unit,
    newOccupation: String,
    onNewOccupationChange: (String) -> Unit,
    selectedPartners: List<Partner>,
    onSelectedPartnersChange: (List<Partner>) -> Unit
) {
    Dialog(onDismissRequest = {
        onAddNewPartnerDialogShownChange(false)
    }) {
        var newCountryCode by remember { mutableStateOf("") }
        val countryCodes = listOf("+11", "+44", "+91", "+49", "+33")
        var selectedCountryCode by remember { mutableStateOf(countryCodes[0]) }
        var expanded by remember { mutableStateOf(false) }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(12.dp)
                ), // ✅ Set full background
            color = Color.White, // ✅ Ensures the Surface matches the background
            shape = RoundedCornerShape(12.dp) // Optional rounded corners
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    "Add new partner",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newName,
                    onValueChange = { newText -> onNewNameChange(newText) },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 60.dp)
                        .padding(horizontal = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words // Automatically capitalize the first letter of each word
                    ),
                    visualTransformation = VisualTransformation.None
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newInstagram,
                    onValueChange = { newText -> onNewInstagramChange(newText) },
                    label = { Text("Instagram") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newOccupation,
                    onValueChange = { newText -> onNewOccupationChange(newText) },
                    label = { Text("Occupation") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.weight(0.4f) // Adjust width ratio for country code field
                    ) {
                        OutlinedTextField(
                            value = selectedCountryCode,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                                .padding(start = 12.dp, end = 0.dp), // Fill the available space
                            label = { Text("") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown",
                                    modifier = Modifier.padding(0.dp) // Adjust the padding for the icon
                                )
                            },
                            singleLine = true, // Prevent text from wrapping to multiple lines
                            maxLines = 1,
                            // Ensure there is only one line
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            countryCodes.forEach { countryCode ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = countryCode,
                                            maxLines = 1
                                        )
                                    },// Prevent wrapping for dropdown tex)
                                    onClick = {
                                        selectedCountryCode = countryCode
                                    }
                                )
                            }
                        }
                    }

                    // Phone number field
                    OutlinedTextField(
                        value = newPhoneNumber,
                        onValueChange = { newText ->
                            onNewPhoneNumberChange(newText)
                        },
                        label = { Text("Phone") },
                        modifier = Modifier
                            .weight(0.6f) // Adjust width ratio for phone number field
                            .padding(start = 2.5.dp, end = 10.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone
                        ),
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newNote,
                    onValueChange = { newText -> onNewNoteChange(newText) },
                    label = { Text("Note") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                        onClick = {
                            val newPartner = Partner(
                                name = newName,
                                instagram = newInstagram,
                                occupation = newOccupation,
                                phoneNumber = newPhoneNumber,
                                note = newNote
                            )
                            onSelectedPartnersChange(selectedPartners + newPartner) // Add to the list
                            onAddPartnerDialogShownChange(false)
                            onAddNewPartnerDialogShownChange(false)
                            onNewNameChange("")
                            onNewInstagramChange("")
                            onNewOccupationChange("")
                            onNewPhoneNumberChange("")
                            onNewNoteChange("")
                        }
                    ) {
                        Text("Save")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onAddPartnerDialogShownChange(false)
                            onAddNewPartnerDialogShownChange(false)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}
