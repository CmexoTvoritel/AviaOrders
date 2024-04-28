package com.effmobile.aviaorders.data.search.repository

import com.effmobile.aviaorders.data.search.model.SearchModeDto
import com.effmobile.aviaorders.data.search.model.toDomain
import com.effmobile.aviaorders.data.search.service.SearchApiService
import com.effmobile.aviaorders.domain.model.search.SearchModel
import com.effmobile.aviaorders.domain.repository.search.SearchRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val service: SearchApiService
): SearchRepository {

    override fun getTicketOffers(): Flow<SearchModel> {
        return flow {
            val response = service.getTicketsSearch()
            val responseString = response.string()
            emit(convertJsonToModel(gson = responseString).toDomain())
        }.flowOn(Dispatchers.IO).catch { exception ->
            val a = exception
        }
    }

    private fun convertJsonToModel(
        gson: String
    ): SearchModeDto = Gson().fromJson(gson, SearchModeDto::class.java)
}