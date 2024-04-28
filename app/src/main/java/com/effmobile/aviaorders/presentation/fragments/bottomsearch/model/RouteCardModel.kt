package com.effmobile.aviaorders.presentation.fragments.bottomsearch.model

import androidx.annotation.DrawableRes

data class RouteCardModel (
    val cardType: CardType,
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

enum class CardType{
    START,
    MIDDLE,
    END
}