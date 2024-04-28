package com.effmobile.aviaorders.presentation.fragments.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.effmobile.aviaorders.core.base.ViewBindingBaseFragment
import com.effmobile.aviaorders.core.extensions.repeatOnStarted
import com.effmobile.aviaorders.databinding.FragmentTicketsBinding
import com.effmobile.aviaorders.presentation.fragments.tickets.adapter.TicketsAdapter
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketAction
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketEvent
import com.effmobile.aviaorders.presentation.fragments.tickets.viewmodel.TicketsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TicketsFragment : ViewBindingBaseFragment<FragmentTicketsBinding>() {

    @Inject
    lateinit var viewModel: TicketsViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTicketsBinding
        get() = FragmentTicketsBinding::inflate

    private var adapter = TicketsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRV()
        setupStates()
        setupActions()
    }

    private fun setupUI() = binding.apply {
        val userTownsFlightInfo = arguments?.getString(argumentTownsRouteInfo)
        ftTownsRoute.text = userTownsFlightInfo ?: ""
        val userFlightDescription = arguments?.getString(argumentFlightInfo)
        ftTownsDescription.text = userFlightDescription ?: ""
    }

    private fun setupRV() = binding.apply {
        ftRVTickets.adapter = adapter
        ftRVTickets.layoutManager = LinearLayoutManager(context)
    }

    private fun setupStates() = binding.apply {
        viewModel.obtainEvent(viewEvent = TicketEvent.LoadInfo)
        repeatOnStarted {
            viewModel.viewStates.collect { state ->
                if(state == null)
                    return@collect
                adapter.submitList(state.listTickets)
            }
        }
    }

    private fun setupActions() = binding.apply {
        ftBackButton.setOnClickListener {
            viewModel.obtainEvent(viewEvent = TicketEvent.BackButtonClicked)
        }
        repeatOnStarted {
            viewModel.viewActions.collect { action ->
                when(action) {
                    is TicketAction.NavigateBack -> navigateBack()
                }
            }
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    companion object {
        private const val argumentTownsRouteInfo = "USER_TOWNS_ROUTE"
        private const val argumentFlightInfo = "USER_DESC_INFO"
    }
}