package com.example.ganym8.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.ganym8.models.Activity
import androidx.compose.material3.*


@Composable
fun ActivitiesDialog(
    activities: List<Activity>,
    showActivitiesDialog: Boolean,
    selectedActivities: MutableList<Activity>, // Immutable list from parent
    onUpdateSelectedActivities: (List<Activity>) -> Unit, // Callback to update the parent
    onDismissDialog: () -> Unit
) {
    // Directly use the mutable list in the dialog
    val selectedButtons = remember { mutableStateOf(mutableMapOf<String, Boolean>()) }

    AlertDialog(
        onDismissRequest = { onDismissDialog() },
        confirmButton = {
            Button(
                onClick = {
                    // Pass the updated selected activities list back to the parent
                    onUpdateSelectedActivities(selectedActivities)
                    onDismissDialog() // Dismiss the dialog after saving
                }
            ) {
                Text("Add and save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismissDialog() }) {
                Text("Cancel")
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                activities.forEach { activity ->
                    // Get the current selection state for this activity
                    val isSelected = selectedButtons.value[activity.name] ?: false

                    Button(
                        onClick = {
                            // Toggle the selected state
                            selectedButtons.value = selectedButtons.value.toMutableMap().apply {
                                this[activity.name] = !isSelected
                            }

                            // Update the selectedActivities list in the dialog
                            if (isSelected) {
                                // If already selected, remove it from the list
                                selectedActivities.remove(activity)
                            } else {
                                // If not selected, add it to the list
                                selectedActivities.add(activity)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color.Gray else MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = if (isSelected) Icons.Default.Check else Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = activity.name, color = Color.White)
                    }
                }
            }
        }
    )
}
