package com.example.ganym8.ui.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import com.example.ganym8.models.Encounter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*



@Composable
fun SexActItem(sexAct: Encounter) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Date: ${sexAct.date}")
        }
    }
}