package com.effmobile.aviaorders.domain.usecase.search

import com.effmobile.aviaorders.domain.repository.search.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    operator fun invoke() = repository.getTicketOffers()
}