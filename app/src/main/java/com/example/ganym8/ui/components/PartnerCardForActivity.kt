package com.example.ganym8.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import com.example.ganym8.models.Encounter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.ganym8.models.Partner


@Composable
fun PartnerCardForActivity(partner: Partner) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        var isGiverChecked by remember { mutableStateOf(false) }
        var isTakerChecked by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Face , // Replace with any icon you prefer
                    contentDescription = "Partner Icon",
                    modifier = Modifier.size(24.dp) // Adjust size as needed
                )
                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
                Text(partner.name)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccountCircle , // Replace with any icon you prefer
                    contentDescription = "Partner Icon",
                    modifier = Modifier.size(24.dp) // Adjust size as needed
                )
                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
                Text(partner.instagram)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isGiverChecked,
                    onCheckedChange = { isGiverChecked = it }
                )
                Text(text = "I did it to him", modifier = Modifier.clickable { isGiverChecked = !isGiverChecked })
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isTakerChecked,
                    onCheckedChange = { isTakerChecked = it }
                )
                Text(text = "He did it to me", modifier = Modifier.clickable { isTakerChecked = !isTakerChecked })
            }
        }
    }
}
