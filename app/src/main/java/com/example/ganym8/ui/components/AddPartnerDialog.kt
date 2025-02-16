package com.example.ganym8.ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog


@Composable
fun AddPartnerDialog(
    onAddPartnerDialogShownChange: (Boolean) -> Unit,
    onAddExistingPartnerDialogShownChange: (Boolean) -> Unit,
    onAddNewPartnerDialogShownChange: (Boolean) -> Unit,
) {
    Dialog(onDismissRequest = { onAddPartnerDialogShownChange(false) }) { // ✅ Use Dialog instead of AlertDialog
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp)), // ✅ Set full background
            color = Color.White, // ✅ Ensures the Surface matches the background
            shape = RoundedCornerShape(12.dp), // Optional rounded corners
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = { onAddNewPartnerDialogShownChange(true) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add New Partner")
                        Text("Add New Partner", textAlign = TextAlign.Center)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onAddExistingPartnerDialogShownChange(true) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Select Existing Partner")
                        Text("Select Existing Partner", textAlign = TextAlign.Center)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { onAddPartnerDialogShownChange(false) }) {
                    Text("Cancel")
                }
            }
        }
    }
}
