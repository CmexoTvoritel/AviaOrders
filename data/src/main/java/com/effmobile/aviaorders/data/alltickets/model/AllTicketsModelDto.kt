package com.effmobile.aviaorders.data.alltickets.model

import com.effmobile.aviaorders.domain.model.alltickets.AllTicketsModel
import com.effmobile.aviaorders.domain.model.alltickets.Arrival
import com.effmobile.aviaorders.domain.model.alltickets.Departure
import com.effmobile.aviaorders.domain.model.alltickets.HandLuggage
import com.effmobile.aviaorders.domain.model.alltickets.Luggage
import com.effmobile.aviaorders.domain.model.alltickets.Price
import com.effmobile.aviaorders.domain.model.alltickets.TicketModel
import com.google.gson.annotations.SerializedName

data class AllTicketsModelDto (
    val tickets: List<TicketModelDto>
)

fun AllTicketsModelDto.toDomain() = AllTicketsModel(
    tickets = this.tickets.map { it.toDomain() }
)

data class TicketModelDto(
    val id: Int,
    val badge: String? = null,
    val price: PriceDto,
    @SerializedName("provider_name") val providerName: String,
    val company: String,
    val departure: DepartureDto,
    val arrival: ArrivalDto,
    @SerializedName("has_transfer") val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    val luggage: LuggageDto? = null,
    @SerializedName("hand_luggage") val handLuggage: HandLuggageDto,
    @SerializedName("is_returnable") val isReturnable: Boolean,
    @SerializedName("is_exchangable") val isExchangeable: Boolean
)

fun TicketModelDto.toDomain() = TicketModel(
    id = this.id,
    badge = this.badge,
    price = this.price.toDomain(),
    providerName = this.providerName,
    company = this.company,
    departure = this.departure.toDomain(),
    arrival = this.arrival.toDomain(),
    hasTransfer = this.hasTransfer,
    hasVisaTransfer = this.hasVisaTransfer,
    luggage = this.luggage?.toDomain(),
    handLuggage = this.handLuggage.toDomain(),
    isReturnable = this.isReturnable,
    isExchangeable = this.isExchangeable
)

data class PriceDto(
    val value: Int
)

fun PriceDto.toDomain() = Price(
    value = this.value
)

data class DepartureDto(
    val town: String,
    val date: String,
    val airport: String
)

fun DepartureDto.toDomain() = Departure(
    town = this.town,
    date = this.date,
    airport = this.airport
)

data class ArrivalDto(
    val town: String,
    val date: String,
    val airport: String
)

fun ArrivalDto.toDomain() = Arrival(
    town = this.town,
    date = this.date,
    airport = this.airport
)

data class LuggageDto(
    @SerializedName("has_luggage") val hasLuggage: Boolean,
    val price: PriceDto?
)

fun LuggageDto.toDomain() = Luggage(
    hasLuggage = this.hasLuggage,
    price = this.price?.toDomain()
)

data class HandLuggageDto(
    @SerializedName("has_hand_luggage") val hasHandLuggage: Boolean,
    val size: String?
)

fun HandLuggageDto.toDomain() = HandLuggage(
    hasHandLuggage = this.hasHandLuggage,
    size = this.size
)