package com.effmobile.aviaorders.data.search.service

import okhttp3.ResponseBody
import retrofit2.http.GET

interface SearchApiService {

    @GET("/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getTicketsSearch(): ResponseBody
}