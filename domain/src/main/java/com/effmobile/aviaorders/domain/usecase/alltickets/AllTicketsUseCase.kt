package com.effmobile.aviaorders.domain.usecase.alltickets

import com.effmobile.aviaorders.domain.repository.alltickets.AllTicketsRepository
import javax.inject.Inject

class AllTicketsUseCase @Inject constructor(
    private val repository: AllTicketsRepository
) {
    operator fun invoke() = repository.getAllTickets()
}