package com.effmobile.aviaorders.presentation.fragments.tickets.viewmodel

import androidx.lifecycle.viewModelScope
import com.effmobile.aviaorders.core.base.viewmodel.BaseViewModel
import com.effmobile.aviaorders.domain.usecase.alltickets.AllTicketsUseCase
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketAction
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketEvent
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketModel
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketUIState
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.round

@FragmentScoped
class TicketsViewModel @Inject constructor(
    private val repository: AllTicketsUseCase
): BaseViewModel<TicketUIState, TicketAction, TicketEvent>() {

    private fun loadInfo() = viewModelScope.launch {
        repository().collect { ticketsInfo ->
            val ticketsDomainList = ticketsInfo.tickets
            val ticketsUI = ticketsDomainList.map {
                TicketModel(
                    badge = it.badge,
                    price = formPriceStr(price = it.price.value) + " ₽",
                    departureDate = formDate(date = it.departure.date),
                    arrivalDate = formDate(date = it.arrival.date),
                    departureAirport = it.departure.airport,
                    arrivalAirport = it.arrival.airport,
                    timeFlight = formFlightTime(
                        dateStart = it.departure.date,
                        dateEnd = it.arrival.date
                    ),
                    hasTransfer = it.hasTransfer
                )
            }
            updateViewState(state = TicketUIState(listTickets = ticketsUI))
        }
    }

    private fun formPriceStr(price: Int): String {
        return String.format("%,d", price).replace(",", " ")
    }

    private fun formDate(date: String) = date.substring(11, 16)

    private fun formFlightTime(dateStart: String, dateEnd: String): String {
        val dateStartTime = LocalDateTime.parse(dateStart)
        val dateEndTime = LocalDateTime.parse(dateEnd)
        val durationInMinutes = Duration.between(dateStartTime, dateEndTime).toMinutes()
        val hours = round(durationInMinutes / 60.0 * 2) / 2
        return "${hours}ч в пути"
    }

    override fun obtainEvent(viewEvent: TicketEvent) {
        when(viewEvent) {
            is TicketEvent.LoadInfo -> loadInfo()
            is TicketEvent.BackButtonClicked -> sendViewAction(action = TicketAction.NavigateBack)
        }
    }
}