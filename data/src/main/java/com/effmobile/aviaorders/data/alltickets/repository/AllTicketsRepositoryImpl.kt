package com.effmobile.aviaorders.data.alltickets.repository

import com.effmobile.aviaorders.data.alltickets.model.AllTicketsModelDto
import com.effmobile.aviaorders.data.alltickets.model.toDomain
import com.effmobile.aviaorders.data.alltickets.service.AllTicketsService
import com.effmobile.aviaorders.domain.model.alltickets.AllTicketsModel
import com.effmobile.aviaorders.domain.repository.alltickets.AllTicketsRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AllTicketsRepositoryImpl @Inject constructor(
    private val service: AllTicketsService
): AllTicketsRepository {

    override fun getAllTickets(): Flow<AllTicketsModel> {
        return flow {
            val response = service.getAllTickets()
            val responseString = response.string()
            emit(convertJsonToModel(gson = responseString).toDomain())
        }.flowOn(Dispatchers.IO).catch { exception ->
            val a = exception
        }
    }

    private fun convertJsonToModel(
        gson: String
    ): AllTicketsModelDto = Gson().fromJson(gson, AllTicketsModelDto::class.java)
}