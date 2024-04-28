package com.effmobile.aviaorders.data.alltickets.service

import okhttp3.ResponseBody
import retrofit2.http.GET

interface AllTicketsService {

    @GET("/uc?id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA&export=download")
    suspend fun getAllTickets(): ResponseBody
}