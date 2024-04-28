package com.effmobile.aviaorders.presentation.fragments.bottomsearch.viewmodel

import com.effmobile.aviaorders.core.base.viewmodel.BaseViewModel
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.BottomSearchAction
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.BottomSearchEvent
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.BottomSearchUIState
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.repository.BottomSheetSearchRepository
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class BottomSearchViewModel @Inject constructor(
    private val repository: BottomSheetSearchRepository
): BaseViewModel<BottomSearchUIState, BottomSearchAction, BottomSearchEvent>() {

    override fun obtainEvent(viewEvent: BottomSearchEvent) {
        when(viewEvent) {
            is BottomSearchEvent.LoadInfo -> updateViewState(state = repository.getRouteCardsList())
            is BottomSearchEvent.ClearInputEvent -> sendViewAction(action = BottomSearchAction.ClearInputAction)
            is BottomSearchEvent.InputDone -> sendViewAction(action = BottomSearchAction.GoToMainSearch)
            is BottomSearchEvent.CardSearchClicked -> sendViewAction(
                action = BottomSearchAction.SetTargetTown(town = viewEvent.town)
            )
            is BottomSearchEvent.DifficultRouteClicked -> {}
            is BottomSearchEvent.AnywhereRouteClicked -> sendViewAction(
                action = BottomSearchAction.SetTargetTown("Куда угодно")
            )
            is BottomSearchEvent.WeekendClicked -> {}
            is BottomSearchEvent.HotClicked -> {}
        }
    }


}