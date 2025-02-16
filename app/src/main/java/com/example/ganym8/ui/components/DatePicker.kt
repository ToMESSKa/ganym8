package com.example.ganym8.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun DatePicker(
    isDatePickerDialogShown: Boolean,
    date: String,
    onDateChange: (String) -> Unit,
    onDatePickerDialogShownChange: (Boolean) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    OutlinedTextField(
        value = date,
        onValueChange = {},
        label = { Text("select date") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDatePickerDialogShownChange(true) }, // Show date picker on click
        trailingIcon = {
            IconButton(onClick = { onDatePickerDialogShownChange(true) }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "select date"
                )
            }
        }
    )

    if (isDatePickerDialogShown) {
        DatePickerModal(
            onDateSelected = { selectedDateMillis ->
                if (selectedDateMillis != null) {
                    onDateChange(dateFormat.format(Date(selectedDateMillis)))
                }
                onDatePickerDialogShownChange(false) // Close the modal
            },
            onDismiss = { onDatePickerDialogShownChange(false) } // Close modal if dismissed
        )
    }
}



