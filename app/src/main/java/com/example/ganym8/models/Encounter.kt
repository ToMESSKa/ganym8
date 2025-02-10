package com.example.ganym8.models

data class Encounter(
    val date: String,
    val partners: List<Partner>,
    val activities: List<Activity>
)
