package com.effmobile.aviaorders.presentation.fragments.bottomsearch.model

data class BottomSearchUIState (
    val list: List<RouteCardModel>
)

sealed interface BottomSearchAction {
    data object ClearInputAction: BottomSearchAction
    data object GoToMainSearch: BottomSearchAction
    data class SetTargetTown(val town: String): BottomSearchAction
}

sealed interface BottomSearchEvent {
    data object LoadInfo: BottomSearchEvent
    data object ClearInputEvent: BottomSearchEvent
    data object InputDone: BottomSearchEvent
    data class CardSearchClicked(val town: String): BottomSearchEvent
    data object DifficultRouteClicked: BottomSearchEvent
    data object AnywhereRouteClicked: BottomSearchEvent
    data object WeekendClicked: BottomSearchEvent
    data object HotClicked: BottomSearchEvent
}