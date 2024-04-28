package com.effmobile.aviaorders.data.search.model

import com.effmobile.aviaorders.domain.model.search.Price
import com.effmobile.aviaorders.domain.model.search.SearchModel
import com.effmobile.aviaorders.domain.model.search.TicketOffer
import com.google.gson.annotations.SerializedName

data class SearchModeDto (
    @SerializedName("tickets_offers") val ticketOffers: List<TicketOfferDto>
)

data class TicketOfferDto(
    val id: Int,
    val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    val price: PriceDto
)

data class PriceDto(
    val value: Int
)

fun SearchModeDto.toDomain() = SearchModel(
    ticketOffers = this.ticketOffers.map { it.toDomain() }
)

fun TicketOfferDto.toDomain() = TicketOffer(
    id = this.id,
    title = this.title,
    timeRange = this.timeRange,
    price = this.price.toDomain()
)

fun PriceDto.toDomain() = Price(
    value = this.value
)