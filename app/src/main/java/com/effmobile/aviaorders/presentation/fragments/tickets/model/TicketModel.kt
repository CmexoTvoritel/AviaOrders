package com.effmobile.aviaorders.presentation.fragments.tickets.model

data class TicketModel (
    val badge: String? = null,
    val price: String,
    val departureDate: String,
    val arrivalDate: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val timeFlight: String,
    val hasTransfer: Boolean
)