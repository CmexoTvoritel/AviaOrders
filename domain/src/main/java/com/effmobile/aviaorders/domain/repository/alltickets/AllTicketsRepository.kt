package com.effmobile.aviaorders.domain.repository.alltickets

import com.effmobile.aviaorders.domain.model.alltickets.AllTicketsModel
import kotlinx.coroutines.flow.Flow

interface AllTicketsRepository {

    fun getAllTickets(): Flow<AllTicketsModel>
}