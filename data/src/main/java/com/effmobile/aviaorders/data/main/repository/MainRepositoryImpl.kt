package com.effmobile.aviaorders.data.main.repository

import com.effmobile.aviaorders.data.main.model.MainModelDto
import com.effmobile.aviaorders.data.main.model.toDomain
import com.effmobile.aviaorders.data.main.service.MainApiService
import com.effmobile.aviaorders.domain.model.main.MainModel
import com.effmobile.aviaorders.domain.repository.main.MainRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: MainApiService
): MainRepository {

    override fun getOffersInfo(): Flow<MainModel> {
        return flow {
            val response = service.getMainCards()
            val responseString = response.string()
            emit(convertJsonToModel(responseString).toDomain())
        }.flowOn(Dispatchers.IO).catch { exception ->
            //TODO: Implement exception
        }
    }

    private fun convertJsonToModel(
        gson: String
    ): MainModelDto = Gson().fromJson(gson, MainModelDto::class.java)
}