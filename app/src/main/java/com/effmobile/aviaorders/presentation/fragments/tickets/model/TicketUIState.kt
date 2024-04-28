package com.effmobile.aviaorders.presentation.fragments.tickets.model

data class TicketUIState (
    val listTickets: List<TicketModel>
)

sealed interface TicketAction {
    data object NavigateBack: TicketAction
}

sealed interface TicketEvent {
    data object LoadInfo: TicketEvent
    data object BackButtonClicked: TicketEvent
}