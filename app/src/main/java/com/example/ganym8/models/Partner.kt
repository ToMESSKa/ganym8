package com.example.ganym8.models

import java.time.LocalDate

// Data class to represent a Sex Act
data class Partner(
    val name: String,  // Date of the sex act (you can use a different type for better date handling)
    val instagram: String,
    val occupation: String,
    val phoneNumber: String,
    val note: String,
    val lastEncounter: LocalDate
// Type of the sex act (you can modify this to be an enum or more complex type if needed)
)
