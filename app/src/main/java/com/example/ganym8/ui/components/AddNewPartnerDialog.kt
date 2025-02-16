package com.example.ganym8.ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.ganym8.models.Partner


@Composable
fun AddNewPartnerDialog(onAddPartnerDialogShownChange : (Boolean) -> Unit,
                        onAddNewPartnerDialogShownChange : (Boolean) -> Unit,
                        newName: String,
                        onNewNameChange : (String) -> Unit,
                        newInstagram: String,
                        onNewInstagramChange : (String) -> Unit,
                        selectedPartners: List<Partner>,
                        onSelectedPartnersChange: (List<Partner>) -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onAddNewPartnerDialogShownChange(false)
        },
        confirmButton = {
            Button(onClick = {
                val newPartner = Partner(name = newName, instagram = newInstagram)
                onSelectedPartnersChange(selectedPartners + newPartner)  // Add to the list
                onAddPartnerDialogShownChange(false)
                onAddNewPartnerDialogShownChange(false)
                onNewNameChange("")
                onNewInstagramChange("")
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = {
                onAddPartnerDialogShownChange(false)
                onAddNewPartnerDialogShownChange(false)
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
                    onValueChange = { newText -> onNewNameChange(newText) },
                    label = { Text("name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newInstagram,
                    onValueChange = { newText ->
                        onNewInstagramChange(newText)
                    },
                    label = { Text("instagram") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        })
}

