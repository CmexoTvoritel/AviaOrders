package com.effmobile.aviaorders.data.main.model

import com.effmobile.aviaorders.domain.model.main.MainModel
import com.effmobile.aviaorders.domain.model.main.Offer
import com.effmobile.aviaorders.domain.model.main.Price

data class MainModelDto (
    val offers: List<OfferDto>
)

data class OfferDto(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDto
)

data class PriceDto(
    val value: Int
)

fun MainModelDto.toDomain() = MainModel(
    offers = this.offers.map { it.toDomain() }
)

fun OfferDto.toDomain() = Offer(
    id = this.id,
    title = this.title,
    town = this.town,
    price = this.price.toDomain()
)

fun PriceDto.toDomain() = Price(
    value = this.value
)