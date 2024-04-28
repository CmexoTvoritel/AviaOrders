package com.effmobile.aviaorders.domain.model.alltickets

data class AllTicketsModel (
    val tickets: List<TicketModel>
)

data class TicketModel (
    val id: Int,
    val badge: String? = null,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage? = null,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangeable: Boolean
)

data class Price(
    val value: Int
)

data class Departure(
    val town: String,
    val date: String,
    val airport: String
)

data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    val hasLuggage: Boolean,
    val price: Price?
)

data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String?
)
