package com.effmobile.aviaorders.presentation.fragments.aviaorders

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effmobile.aviaorders.core.base.ViewBindingBaseFragment
import com.effmobile.aviaorders.core.extensions.repeatOnStarted
import com.effmobile.aviaorders.databinding.FragmentAviaOrdersBinding
import com.effmobile.aviaorders.presentation.fragments.aviaorders.adapter.AviaOrdersAdapter
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.AviaOrdersAction
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.AviaOrdersEvent
import com.effmobile.aviaorders.presentation.fragments.aviaorders.viewmodel.AviaOrdersViewModel
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.BottomSheetSearchFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AviaOrdersFragment : ViewBindingBaseFragment<FragmentAviaOrdersBinding>() {

    @Inject
    lateinit var viewModel: AviaOrdersViewModel

    private var adapter = AviaOrdersAdapter()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAviaOrdersBinding
        get() = FragmentAviaOrdersBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupStates()
        setupActions()
    }

    private fun setupRV() = binding.apply {
        faoRVCards.adapter = adapter
    }

    private fun setupStates() = binding.apply {
        viewModel.obtainEvent(viewEvent = AviaOrdersEvent.LoadInfo)
        repeatOnStarted {
            viewModel.viewStates.collect { state ->
                if(state == null)
                    return@collect
                adapter.submitList(state.list.offers)
                setupDepartureTown(town = state.userInputTown)
            }
        }
    }

    private fun setupActions() = binding.apply {
        faoInputToText.setOnClickListener {
            viewModel.obtainEvent(viewEvent = AviaOrdersEvent.OpenBottomSheetMenu)
        }
        faoInputFromText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(newText: Editable?) {
                viewModel.obtainEvent(viewEvent = AviaOrdersEvent.SaveNewUserData(
                    newTown = newText.toString()
                ))
            }

        })
        repeatOnStarted {
            viewModel.viewActions.collect { action ->
                when(action) {
                    is AviaOrdersAction.OpenBottomSheetMenu -> openBottomSheet()
                }
            }
        }
    }

    private fun setupDepartureTown(town: String) = binding.apply {
        faoInputFromText.setText(town)
    }

    private fun openBottomSheet() {
        val bottomSheetSearchFragment = BottomSheetSearchFragment()
        val bundle = Bundle()
        bundle.putString("USER_INPUT_DEPARTURE_TOWN", binding.faoInputFromText.text.toString())
        bottomSheetSearchFragment.arguments = bundle
        bottomSheetSearchFragment.show(requireActivity().supportFragmentManager, "searchRoutes")
    }

}