package com.effmobile.aviaorders.presentation.fragments.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.effmobile.aviaorders.core.base.viewmodel.BaseViewModel
import com.effmobile.aviaorders.domain.usecase.search.SearchUseCase
import com.effmobile.aviaorders.presentation.fragments.search.model.DateType
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchAction
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchEvent
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchModel
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchUIState
import com.effmobile.aviaorders.presentation.fragments.search.model.TicketOffer
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): BaseViewModel<SearchUIState, SearchAction, SearchEvent>() {

    private fun loadInfo() = viewModelScope.launch {
        searchUseCase().collect { info ->
            val flightList = SearchModel(
                ticketOffers = info.ticketOffers.map {
                    TicketOffer(
                        id = it.id,
                        title = it.title,
                        timeRange = formattedDateStr(listDate = it.timeRange),
                        price = formattedPriceStr(price = it.price.value) + " ₽"
                    )
                }
            )
            updateViewState(state = SearchUIState(listFlights = flightList))
        }
    }

    private fun formattedDateStr(listDate: List<String>): String {
        var answer = ""
        listDate.forEach { date ->
            answer += "$date "
        }
        return answer
    }

    private fun formattedPriceStr(price: Int): String {
        return String.format("%,d", price).replace(",", " ")
    }

    private fun setDateForStartFlight(day: Int, month: Int, dayOfWeek: Int) {
        val dateStr = setupDate(day = day, month = month)
        val dayOfWeekStr = setupDayOfWeek(dayOfWeek = dayOfWeek)
        sendViewAction(action = SearchAction.SetStartFlightDate(date = dateStr, dayOfWeek = dayOfWeekStr))
    }

    private fun setDateForBackFlight(day: Int, month: Int, dayOfWeek: Int) {
        val dateStr = setupDate(day = day, month = month)
        val dayOfWeekStr = setupDayOfWeek(dayOfWeek = dayOfWeek)
        sendViewAction(action = SearchAction.SetBackFlightDate(date = dateStr, dayOfWeek = dayOfWeekStr))
    }

    private fun setupDate(day: Int, month: Int): String {
        var answer = "$day "
        when(month) {
            0 -> answer += "янв"
            1 -> answer += "фев"
            2 -> answer += "мар"
            3 -> answer += "апр"
            4 -> answer += "май"
            5 -> answer += "июн"
            6 -> answer += "июл"
            7 -> answer += "авг"
            8 -> answer += "сен"
            9 -> answer += "окт"
            10 -> answer += "ноя"
            11 -> answer += "дек"
        }
        return answer
    }

    private fun setupDayOfWeek(dayOfWeek: Int): String {
        var answer = ""
        when(dayOfWeek) {
            1 -> answer += ", вс"
            2 -> answer += ", пн"
            3 -> answer += ", вт"
            4 -> answer += ", ср"
            5 -> answer += ", чт"
            6 -> answer += ", пт"
            7 -> answer += ", сб"
        }
        return answer
    }

    override fun obtainEvent(viewEvent: SearchEvent) {
        when(viewEvent) {
            is SearchEvent.LoadInfo -> loadInfo()
            is SearchEvent.ReverseRoutesClicked -> sendViewAction(action = SearchAction.ReverseRoutesAction)
            is SearchEvent.ClearTextClicked -> sendViewAction(action = SearchAction.ClearTextAction)
            is SearchEvent.BackIconClicked -> sendViewAction(action = SearchAction.BackIconAction)
            is SearchEvent.StartFlightClicked -> sendViewAction(
                action = SearchAction.OpenCalendarAction(dateType = DateType.START)
            )
            is SearchEvent.BackFlightClicked -> sendViewAction(
                action = SearchAction.OpenCalendarAction(dateType = DateType.BACK)
            )
            is SearchEvent.StartFlightDataPicked -> setDateForStartFlight(
                day = viewEvent.day,
                month = viewEvent.month,
                dayOfWeek = viewEvent.dayOfWeek
            )
            is SearchEvent.BackFlightDataPicked -> setDateForBackFlight(
                day = viewEvent.day,
                month = viewEvent.month,
                dayOfWeek = viewEvent.dayOfWeek
            )
            is SearchEvent.WatchAllTicketsClicked -> sendViewAction(action = SearchAction.NavigateToTickets)
        }
    }
}