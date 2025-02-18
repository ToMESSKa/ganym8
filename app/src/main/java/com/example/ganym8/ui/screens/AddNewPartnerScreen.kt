package com.example.ganym8.ui.screens

import PartnerViewModel
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.ganym8.R
import com.example.ganym8.models.Partner
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPartnerScreen(
    navController: NavController,
    partnerViewModel: PartnerViewModel // Receive the ViewModel
) {
    // Example data from the ViewModel
    var newName by remember { mutableStateOf("") }
    var newInstagram by remember { mutableStateOf("") }
    var newPhoneNumber by remember { mutableStateOf("") }
    var newNote by remember { mutableStateOf("") }
    var newOccupation by remember { mutableStateOf("") }
    var newCountryCode by remember { mutableStateOf("") }
    val countryCodes = listOf("+36", "+44", "+91", "+49", "+33")
    var selectedCountryCode by remember { mutableStateOf(countryCodes[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, // Centers the items horizontally
            verticalAlignment = Alignment.CenterVertically, // Centers items vertically
            modifier = Modifier.fillMaxWidth() // Ensures the Row takes the full width
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person_fill_add), // Load custom SVG icon
                contentDescription = "Custom Icon",
                modifier = Modifier.size(40.dp) // Adjust size if needed
            )

            Spacer(modifier = Modifier.width(8.dp)) // Optional space between icon and text

            Text(text = "Add new partner")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newName,
            onValueChange = { newName = it },
            label = { Text("name") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.person_circle), // Replace with your desired icon
                    contentDescription = "Name Icon",
                    modifier = Modifier.size(20.dp) // Adjust size if needed
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words)// Ensure the text field stays on a single line
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Instagram Input Field
        OutlinedTextField(
            value = newInstagram,
            onValueChange = { newInstagram = it },
            label = { Text("instagram") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.instagram), // Replace with your desired icon
                    contentDescription = "Name Icon",
                    modifier = Modifier.size(20.dp) // Adjust size if needed
                )
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Occupation Input Field
        OutlinedTextField(
            value = newOccupation,
            onValueChange = { newOccupation = it },
            label = { Text("occupation") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
                    leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.tools), // Replace with your desired icon
                    contentDescription = "Name Icon",
                    modifier = Modifier.size(20.dp) // Adjust size if needed
                )
            },
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Phone number Field
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(0.3f) // Adjust width ratio for country code field
            ) {
                OutlinedTextField(
                    value = selectedCountryCode,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
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
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
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
                            },
                            modifier = Modifier.background(Color.White)
                        )
                    }
                }
            }

            // Phone number field
            OutlinedTextField(
                value = newPhoneNumber,
                onValueChange = {
                    newPhoneNumber = it
                },
                label = { Text("phone") },
                modifier = Modifier
                    .weight(0.7f) // Adjust width ratio for phone number field
                    .padding(start = 2.5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.telephone_fill), // Replace with your desired icon
                        contentDescription = "Name Icon",
                        modifier = Modifier.size(20.dp) // Adjust size if needed
                    )
                },

            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Note Input Field
        OutlinedTextField(
            value = newNote,
            onValueChange = { newNote = it },
            label = { Text("note") },
            modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.journal_check), // Replace with your desired icon
                    contentDescription = "Name Icon",
                    modifier = Modifier.size(20.dp) // Adjust size if needed
                )
            },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Save Button
            Button(
                onClick = {
                    val newPartner = Partner(
                        name = newName,
                        instagram = newInstagram,
                        occupation = newOccupation,
                        phoneNumber = selectedCountryCode + newPhoneNumber,
                        note = newNote,
                        lastEncounter = LocalDate.now()
                    )
                    partnerViewModel.addPartner(newPartner) // Save the new partner using the ViewModel
                    navController.navigateUp() // Go back to the previous screen
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }

            // Cancel Button
            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)

            ) {
                Text("Cancel")
            }
        }
    }
}

