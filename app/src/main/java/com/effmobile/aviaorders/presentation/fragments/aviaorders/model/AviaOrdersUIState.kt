package com.effmobile.aviaorders.presentation.fragments.aviaorders.model

data class AviaOrdersUIState (
    val list: AviaOrdersModel,
    val userInputTown: String,
)

sealed interface AviaOrdersAction {
    data object OpenBottomSheetMenu: AviaOrdersAction
}

sealed interface AviaOrdersEvent {
    data object LoadInfo: AviaOrdersEvent
    data object OpenBottomSheetMenu: AviaOrdersEvent
    data class SaveNewUserData(val newTown: String): AviaOrdersEvent
}