package com.effmobile.aviaorders.presentation.fragments.bottomsearch.repository

import android.content.Context
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.BottomSearchUIState
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.CardType
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.RouteCardModel
import dagger.hilt.android.qualifiers.ApplicationContext
import com.effmobile.aviaorders.resources.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BottomSheetSearchRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getRouteCardsList() = BottomSearchUIState(list = listOf(
        RouteCardModel(
            cardType = CardType.START,
            image = R.drawable.ic_route_image_one,
            title = context.getString(R.string.fragment_bottom_card_title_one),
            description = context.getString(R.string.fragment_bottom_card_description)
        ),
        RouteCardModel(
            cardType = CardType.MIDDLE,
            image = R.drawable.ic_route_image_two,
            title = context.getString(R.string.fragment_bottom_card_title_second),
            description = context.getString(R.string.fragment_bottom_card_description)
        ),
        RouteCardModel(
            cardType = CardType.END,
            image = R.drawable.ic_route_image_three,
            title = context.getString(R.string.fragment_bottom_card_title_third),
            description = context.getString(R.string.fragment_bottom_card_description)
        )
    ))
}