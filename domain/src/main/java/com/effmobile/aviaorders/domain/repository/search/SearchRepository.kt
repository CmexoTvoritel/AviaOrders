package com.effmobile.aviaorders.domain.repository.search

import com.effmobile.aviaorders.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getTicketOffers(): Flow<SearchModel>
}