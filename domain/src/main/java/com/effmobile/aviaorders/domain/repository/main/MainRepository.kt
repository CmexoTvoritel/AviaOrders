package com.effmobile.aviaorders.domain.repository.main

import com.effmobile.aviaorders.domain.model.main.MainModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getOffersInfo(): Flow<MainModel>
}