package com.effmobile.aviaorders.presentation.fragments.aviaorders.viewmodel

import androidx.lifecycle.viewModelScope
import com.effmobile.aviaorders.core.base.viewmodel.BaseViewModel
import com.effmobile.aviaorders.core.utils.PreferencesManager
import com.effmobile.aviaorders.domain.usecase.main.MainUseCase
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.AviaOrdersAction
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.AviaOrdersEvent
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.AviaOrdersModel
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.AviaOrdersUIState
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.Offer
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.Price
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class AviaOrdersViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val preferencesManager: PreferencesManager
): BaseViewModel<AviaOrdersUIState, AviaOrdersAction, AviaOrdersEvent>() {

    private fun loadInfo() = viewModelScope.launch {
        mainUseCase().collect { info ->
            val needList = AviaOrdersModel(
                offers = info.offers.map { Offer(
                        id = it.id,
                        title = it.title,
                        town = it.town,
                        price = Price(it.price.value)
                ) }
            )
            updateViewState(state = AviaOrdersUIState(
                list = needList,
                userInputTown = preferencesManager.departureUserTown ?: ""
            ))
        }
    }

    override fun obtainEvent(viewEvent: AviaOrdersEvent) {
        when(viewEvent) {
            is AviaOrdersEvent.LoadInfo -> loadInfo()
            is AviaOrdersEvent.OpenBottomSheetMenu -> sendViewAction(
                action = AviaOrdersAction.OpenBottomSheetMenu
            )
            is AviaOrdersEvent.SaveNewUserData -> preferencesManager.departureUserTown = viewEvent.newTown
        }
    }
}