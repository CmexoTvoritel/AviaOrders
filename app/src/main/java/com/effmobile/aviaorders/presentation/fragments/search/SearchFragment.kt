package com.effmobile.aviaorders.presentation.fragments.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.effmobile.aviaorders.core.base.ViewBindingBaseFragment
import com.effmobile.aviaorders.core.extensions.repeatOnStarted
import com.effmobile.aviaorders.databinding.FragmentSearchBinding
import com.effmobile.aviaorders.presentation.fragments.search.model.DateType
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchAction
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchEvent
import com.effmobile.aviaorders.presentation.fragments.search.model.SearchModel
import com.effmobile.aviaorders.presentation.fragments.search.viewmodel.SearchViewModel
import com.effmobile.aviaorders.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : ViewBindingBaseFragment<FragmentSearchBinding>() {

    @Inject
    lateinit var viewModel: SearchViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupStates()
        setupActions()
    }

    private fun setupUI() = binding.apply {
        val userDepartureTownInfo = arguments?.getString(argumentUserDepartureTownKey)
        fsInputFromText.text = userDepartureTownInfo ?: ""
        val userArrivalTownInfo = arguments?.getString(argumentUserArrivalTownKey)
        fsInputToText.text = userArrivalTownInfo ?: ""
    }

    private fun setupStates() = binding.apply {
        viewModel.obtainEvent(viewEvent = SearchEvent.LoadInfo)
        repeatOnStarted {
            viewModel.viewStates.collect { state ->
                if(state == null)
                    return@collect
                setupCardFlight(infoCard = state.listFlights)
            }
        }
    }

    private fun setupActions() = binding.apply {
        fsReverseButton.setOnClickListener {
            viewModel.obtainEvent(viewEvent = SearchEvent.ReverseRoutesClicked)
        }
        fsClearText.setOnClickListener {
            viewModel.obtainEvent(viewEvent = SearchEvent.ClearTextClicked)
        }
        fsBackButton.setOnClickListener {
            viewModel.obtainEvent(viewEvent = SearchEvent.BackIconClicked)
        }
        fsStartFlightCard.setOnClickListener {
            viewModel.obtainEvent(viewEvent = SearchEvent.StartFlightClicked)
        }
        fsBackFlightCard.setOnClickListener {
            viewModel.obtainEvent(viewEvent = SearchEvent.BackFlightClicked)
        }
        fsWatchAllTickets.setOnClickListener {
            viewModel.obtainEvent(viewEvent = SearchEvent.WatchAllTicketsClicked)
        }
        repeatOnStarted {
            viewModel.viewActions.collect { action ->
                when(action) {
                    is SearchAction.ReverseRoutesAction -> reverseRoutes()
                    is SearchAction.ClearTextAction -> clearText()
                    is SearchAction.BackIconAction -> navigateBack()
                    is SearchAction.OpenCalendarAction -> openCalendar(dateType = action.dateType)
                    is SearchAction.SetStartFlightDate -> setStartFlightDate(
                        date = action.date, dayOfWeek = action.dayOfWeek
                    )
                    is SearchAction.SetBackFlightDate -> setEndFlightDate(
                        date = action.date, dayOfWeek = action.dayOfWeek
                    )
                    is SearchAction.NavigateToTickets -> navigateToTickets()
                }
            }
        }
    }

    private fun setupCardFlight(infoCard: SearchModel) = binding.apply {
        fsFirstTitle.text = infoCard.ticketOffers[0].title
        fsFirstPrice.text = infoCard.ticketOffers[0].price
        fsFirstTimeRangeText.text = infoCard.ticketOffers[0].timeRange
        fsSecondTitle.text = infoCard.ticketOffers[1].title
        fsSecondPrice.text = infoCard.ticketOffers[1].price
        fsSecondTimeRangeText.text = infoCard.ticketOffers[1].timeRange
        fsThirdTitle.text = infoCard.ticketOffers[2].title
        fsThirdPrice.text = infoCard.ticketOffers[2].price
        fsThirdTimeRangeText.text = infoCard.ticketOffers[2].timeRange
    }

    private fun reverseRoutes() = binding.apply {
        fsInputFromText.text = fsInputToText.text.also {
            fsInputToText.text = fsInputFromText.text
        }
    }

    private fun clearText() = binding.apply {
        fsInputToText.text = ""
        navigateBack()
    }

    private fun navigateBack() = binding.apply {
        findNavController().popBackStack()
    }

    private fun openCalendar(dateType: DateType) = binding.apply {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                viewModel.obtainEvent(
                    viewEvent = if(dateType == DateType.START)
                        SearchEvent.StartFlightDataPicked(
                            day = selectedDay,
                            month = selectedMonth,
                            dayOfWeek = dayOfWeek
                        )
                    else
                        SearchEvent.BackFlightDataPicked(
                            day = selectedDay,
                            month = selectedMonth,
                            dayOfWeek = dayOfWeek
                        )
                )
            }, year, month, day)
        datePickerDialog.show()
    }

    private fun setStartFlightDate(date: String, dayOfWeek: String) = binding.apply {
        fsStartFlightText.text = date
        fsStartFlightDesc.text = dayOfWeek
    }

    private fun setEndFlightDate(date: String, dayOfWeek: String) = binding.apply {

    }

    private fun navigateToTickets() = binding.apply {
        val bundle = Bundle()
        bundle.putString(argumentTownsRouteInfo, "${fsInputFromText.text}-${fsInputToText.text}")
        var flightDateText = fsStartFlightText.text.substring(0, fsStartFlightText.text.indexOf(' ') + 1)
        when(fsStartFlightText.text.substring(fsStartFlightText.text.indexOf(' ') + 1)) {
            "янв" -> flightDateText += "января"
            "фев" -> flightDateText += "февраля"
            "мар" -> flightDateText += "марта"
            "апр" -> flightDateText += "апреля"
            "май" -> flightDateText += "мая"
            "июн" -> flightDateText += "июня"
            "июл" -> flightDateText += "июля"
            "авг" -> flightDateText += "августа"
            "сен" -> flightDateText += "сентября"
            "окт" -> flightDateText += "октября"
            "ноя" -> flightDateText += "ноября"
            "дек" -> flightDateText += "декабря"
        }
        bundle.putString(argumentFlightInfo, "$flightDateText, 1 пассажир")
        findNavController().navigate(R.id.ticketsFragment, bundle)
    }

    companion object {
        private const val argumentUserDepartureTownKey = "USER_INPUT_DEPARTURE_TOWN"
        private const val argumentUserArrivalTownKey = "USER_INPUT_ARRIVAL_TOWN"
        private const val argumentTownsRouteInfo = "USER_TOWNS_ROUTE"
        private const val argumentFlightInfo = "USER_DESC_INFO"
    }
}