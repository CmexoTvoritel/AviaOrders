package com.effmobile.aviaorders.presentation.fragments.search.model

data class SearchModel (
    val ticketOffers: List<TicketOffer>
)

data class TicketOffer (
    val id: Int,
    val title: String,
    val timeRange: String,
    val price: String
)

enum class DateType {
    START,
    BACK
}