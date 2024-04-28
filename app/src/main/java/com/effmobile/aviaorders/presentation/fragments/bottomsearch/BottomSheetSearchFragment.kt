package com.effmobile.aviaorders.presentation.fragments.bottomsearch

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.effmobile.aviaorders.core.base.ViewBindingBaseBottomDialogFragment
import com.effmobile.aviaorders.core.extensions.repeatOnStarted
import com.effmobile.aviaorders.databinding.FragmentBottomSearchLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.adapter.BottomSearchAdapter
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.BottomSearchAction
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.BottomSearchEvent
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.viewmodel.BottomSearchViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.effmobile.aviaorders.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetSearchFragment: ViewBindingBaseBottomDialogFragment<FragmentBottomSearchLayoutBinding>() {

    @Inject
    lateinit var viewModel : BottomSearchViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomSearchLayoutBinding
        get() = FragmentBottomSearchLayoutBinding::inflate

    private var adapter = BottomSearchAdapter()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { it ->
            val bottomSheetDialog = it as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                val layoutParams = it.layoutParams
                val windowHeight = getWindowHeight()
                layoutParams?.height = windowHeight
                it.layoutParams = layoutParams
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRV()
        setupStates()
        setupActions()
    }

    private fun setupUI() = binding.apply {
        val userDepartureTownInfo = arguments?.getString(argumentUserDepartureTownKey)
        fbsInputFromText.text = userDepartureTownInfo ?: ""
    }

    private fun setupRV() = binding.apply {
        fbsRVRoutes.adapter = adapter
        fbsRVRoutes.layoutManager = LinearLayoutManager(context)
        adapter.clickCallback = { item ->
            viewModel.obtainEvent(viewEvent = BottomSearchEvent.CardSearchClicked(town = item.title))
        }
    }

    private fun setupStates() = binding.apply {
        viewModel.obtainEvent(viewEvent = BottomSearchEvent.LoadInfo)
        repeatOnStarted {
            viewModel.viewStates.collect { state ->
                if(state == null)
                    return@collect
                adapter.submitList(state.list)
            }
        }
    }

    private fun setupActions() = binding.apply {
        fbsClearText.setOnClickListener {
            viewModel.obtainEvent(viewEvent = BottomSearchEvent.ClearInputEvent)
        }
        fbsDifficultRouteCard.setOnClickListener {
            viewModel.obtainEvent(viewEvent = BottomSearchEvent.DifficultRouteClicked)
        }
        fbsDifficultAnywhereCard.setOnClickListener {
            viewModel.obtainEvent(viewEvent = BottomSearchEvent.AnywhereRouteClicked)
        }
        fbsWeekendCard.setOnClickListener {
            viewModel.obtainEvent(viewEvent = BottomSearchEvent.WeekendClicked)
        }
        fbsHotCard.setOnClickListener {
            viewModel.obtainEvent(viewEvent = BottomSearchEvent.HotClicked)
        }
        fbsInputToText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.obtainEvent(viewEvent = BottomSearchEvent.InputDone)
                return@setOnEditorActionListener true
            }
            false
        }
        repeatOnStarted {
            viewModel.viewActions.collect { action ->
                when(action) {
                    is BottomSearchAction.ClearInputAction -> { fbsInputToText.text.clear() }
                    is BottomSearchAction.GoToMainSearch -> navigate()
                    is BottomSearchAction.SetTargetTown -> fbsInputToText.setText(action.town)
                }
            }
        }
    }

    private fun navigate() {
        val bundle = Bundle()
        bundle.putString(argumentUserDepartureTownKey, binding.fbsInputFromText.text.toString())
        bundle.putString(argumentUserArrivalTownKey, binding.fbsInputToText.text.toString())
        dialog?.dismiss()
        findNavController().navigate(R.id.searchFragment, bundle)
    }

    companion object {
        private const val argumentUserDepartureTownKey = "USER_INPUT_DEPARTURE_TOWN"
        private const val argumentUserArrivalTownKey = "USER_INPUT_ARRIVAL_TOWN"
    }
}