package com.effmobile.aviaorders.domain.model.main

data class MainModel (
    val offers: List<Offer>
)

data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)

data class Price(
    val value: Int
)