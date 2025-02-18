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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.ganym8.R
import com.example.ganym8.navigation.Screen


@Composable
fun AddPartnerDialog(
    onAddPartnerDialogShownChange: (Boolean) -> Unit,
    onAddExistingPartnerDialogShownChange: (Boolean) -> Unit,
    onAddNewPartnerDialogShownChange: (Boolean) -> Unit,
    navController: NavController
) {
    Dialog(onDismissRequest = { onAddPartnerDialogShownChange(false) }) { // ✅ Use Dialog instead of AlertDialog
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(12.dp)
                ), // ✅ Set full background
            color = Color.White, // ✅ Ensures the Surface matches the background
            shape = RoundedCornerShape(12.dp), // Optional rounded corners
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "Add partner",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Adds spacing between buttons
                    modifier = Modifier.padding(16.dp)
                ) {
                    Button(
                        onClick = {
                            // Hide the dialog immediately
                            onAddPartnerDialogShownChange(false)

                            // Navigate to the new screen immediately
                            navController.navigate(Screen.AddNewPartnerScreen.route)
                        },
                        modifier = Modifier.size(120.dp), // Square button
                        shape = RoundedCornerShape(12.dp), // Rounded corners
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.person_fill_add), // Load custom SVG icon
                                contentDescription = "Custom Icon",
                                modifier = Modifier.size(40.dp) // Adjust size if needed
                            )
                            Text(
                                "add new",
                                maxLines = 1,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                "partner",
                                maxLines = 1,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onAddPartnerDialogShownChange(false)
                            navController.navigate(Screen.AddExistingPartnerScreen.route)
                        },
                        modifier = Modifier.size(120.dp), // Square button
                        shape = RoundedCornerShape(12.dp), // Rounded corners
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.person_fill_check), // Load custom SVG icon
                                contentDescription = "Custom Icon",
                                modifier = Modifier.size(40.dp) // Adjust size if needed
                            )
                            Text(
                                "add existing",
                                //maxLines = 1,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                "partner",
                                maxLines = 1,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onAddPartnerDialogShownChange(false) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                ) {

                    Text("Cancel")
                }
            }
        }
    }
}
