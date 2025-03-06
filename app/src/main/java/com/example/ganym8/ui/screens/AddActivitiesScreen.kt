package com.example.ganym8.ui.screens

import ActivityViewModel
import PartnerViewModel
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign

import androidx.navigation.NavHostController
import com.example.ganym8.models.Partner
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment



@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddActivitiesScreen(
    navController: NavHostController,
    partnerViewModel: PartnerViewModel,
    activityViewModel: ActivityViewModel
) {
    var searchText by remember { mutableStateOf("") }
    var selectedItems by remember { mutableStateOf<List<String>>(emptyList()) }

    val favoriteItems = listOf(
        "blowjob", "rimming",
        "spitting",
    )

    val allItems = listOf(
        "french kissing", "neck kissing", "lip biting", "blowjob", "rimming", "snowballing",
        "spitting", "gagging", "breath control"
    )
    val filteredItems = allItems.filter {
        it.contains(
            searchText,
            ignoreCase = true
        ) && !selectedItems.contains(it)
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            "oral & mouth play",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(16.dp)) {

            // Row for Selected Items (Always has reserved space)
            Text("Selected:")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),

                ) {
                    selectedItems.forEach { item ->
                        AssistChip(
                            onClick = { selectedItems = selectedItems - item },
                            label = { Text(item) },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Space between rows

            // Row for Available Items
            Text("Tap to Select:")
            FlowRow(
                modifier = Modifier.fillMaxWidth(),

            ) {
                favoriteItems.forEach { item ->
                    AssistChip(
                        onClick = { selectedItems = selectedItems + item },
                        label = { Text(item) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }

        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Show Filtered List **ONLY when searchText is not empty**
        if (searchText.isNotEmpty()) {
            LazyColumn {
                items(filteredItems) { item ->
                    ListItem(
                        headlineContent = { Text(item) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedItems = selectedItems + item
                                searchText = ""  // Clear search after selection
                            }
                            .padding(8.dp)
                    )
                    Divider()
                }
            }
        }
    }
}
