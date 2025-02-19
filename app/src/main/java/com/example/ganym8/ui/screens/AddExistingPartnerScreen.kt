package com.example.ganym8.ui.screens

import PartnerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.ganym8.R
import com.example.ganym8.models.Partner
import java.time.LocalDate


@Composable
fun AddExistingPartnerScreen(
    navController: NavController,
    partnerViewModel: PartnerViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val existingPartners = listOf(
        Partner(
            name = "Ömer", instagram = "meryn", occupation = "sales executive", phoneNumber = "555", note = "",
            lastEncounter = LocalDate.now()
        ),
        Partner(
            name = "Alexander", instagram = "alexr", occupation = "chief marketing officer", phoneNumber = "", note = "",
            lastEncounter = LocalDate.now()
        ),
        Partner(
            name = "Balázs", instagram = "balazs69", occupation = "student", phoneNumber = "", note = "",
            lastEncounter = LocalDate.now()
        )
    )

    var searchQuery by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedPartner by remember { mutableStateOf<Partner?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person_fill_check),
                contentDescription = "Custom Icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add existing partner")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box {


            OutlinedTextField(
                value = when {
                    searchQuery.isNotEmpty() -> searchQuery
                    selectedPartner != null -> selectedPartner!!.name
                    else -> "Select an option"  // Ensure this is displayed when nothing is selected
                },
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Select an option") },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", modifier = Modifier.clickable { expanded = true })
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                    .background(Color.White)
                    .align(Alignment.TopStart) // Ensure it starts below the text field
                    .padding(top = 8.dp) // Ensure some spac
                    .heightIn(min = 100.dp) // Ensure the dropdown doesn't shrink too much
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White
                    )
                )

                // Filtered list based on search query
                val filteredPartners = existingPartners.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                            it.instagram.contains(searchQuery, ignoreCase = true) ||
                            it.occupation.contains(searchQuery, ignoreCase = true)
                }

                if (filteredPartners.isEmpty()) {
                    Text(
                        text = "No results found",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Gray
                    )
                } else {
                    filteredPartners.forEach { existingPartner ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(text = existingPartner.name, style = MaterialTheme.typography.bodyLarge)
                                    Text(
                                        text = "instagram: ${existingPartner.instagram}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "occupation: ${existingPartner.occupation}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            },
                            onClick = {
                                selectedPartner = existingPartner
                                searchQuery = "" // Clear search query after selection
                                expanded = false
                            }
                        )
                    }
                }

                // Spacer to prevent dropdown from moving up under the text field when empty
                Spacer(modifier = Modifier.height(8.dp))
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    selectedPartner?.let { partnerViewModel.addPartner(it) }
                    navController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }

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

