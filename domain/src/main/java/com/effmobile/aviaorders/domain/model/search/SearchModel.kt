package com.effmobile.aviaorders.domain.model.search

data class SearchModel (
    val ticketOffers: List<TicketOffer>
)

data class TicketOffer (
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Price
)

data class Price(
    val value: Int
)