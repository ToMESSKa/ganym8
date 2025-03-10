package com.example.ganym8.ui.screens

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.example.ganym8.ui.components.PartnerCard
import PartnerViewModel
import com.example.ganym8.ui.components.PartnerCardForActivity


@Composable
fun AddPartnerToActivity(
    onAddPartnerToActivityDialogShown: (Boolean) -> Unit,
    partnerViewModel: PartnerViewModel
) {
    Dialog(onDismissRequest = { onAddPartnerToActivityDialogShown(false) }) { // ✅ Use Dialog instead of AlertDialog
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(12.dp)
                ), // ✅ Set full background
            color = Color.White, // ✅ Ensures the Surface matches the background
            shape = RoundedCornerShape(12.dp), // Optional rounded corners
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                partnerViewModel.getPartners().forEach { selectedPartner ->
                    PartnerCardForActivity(selectedPartner)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {  }) {
                    Text(text = "Click Me")
                }
            }
        }
    }


}
