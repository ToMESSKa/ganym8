package com.example.ganym8.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun LocationPicker( location: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location Icon",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = location,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { /* Handle click */ },
            modifier = Modifier
                .wrapContentSize() // Ensures button wraps tightly around the content
                .heightIn(min = 25.dp), // Height adjusts based on the content (text)
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray, // Button background color
                contentColor = Color.White // Text color inside the button
            ),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
        ) {
            Text(
                text = "change location",
                style = TextStyle(
                    fontSize = 12.sp // Set the text size to 12sp (smaller size)
                )
            )
        }
    }
}



