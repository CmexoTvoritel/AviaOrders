package com.effmobile.aviaorders.presentation.fragments.search.model

data class SearchUIState (
    val listFlights: SearchModel
)

sealed interface SearchAction {
    data object ReverseRoutesAction: SearchAction
    data object ClearTextAction: SearchAction
    data object BackIconAction: SearchAction
    data class OpenCalendarAction(val dateType: DateType): SearchAction
    data class SetStartFlightDate(val date: String, val dayOfWeek: String): SearchAction
    data class SetBackFlightDate(val date: String, val dayOfWeek: String): SearchAction
    data object NavigateToTickets: SearchAction
}

sealed interface SearchEvent {
    data object LoadInfo: SearchEvent
    data object ReverseRoutesClicked: SearchEvent
    data object ClearTextClicked: SearchEvent
    data object BackIconClicked: SearchEvent
    data object StartFlightClicked: SearchEvent
    data object BackFlightClicked: SearchEvent
    data class StartFlightDataPicked(val day: Int, val month: Int, val dayOfWeek: Int): SearchEvent
    data class BackFlightDataPicked(val day: Int, val month: Int, val dayOfWeek: Int): SearchEvent
    data object WatchAllTicketsClicked: SearchEvent
}