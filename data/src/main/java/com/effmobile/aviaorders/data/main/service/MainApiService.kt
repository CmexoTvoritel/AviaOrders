package com.effmobile.aviaorders.data.main.service

import okhttp3.ResponseBody
import retrofit2.http.GET

interface MainApiService {

    @GET("/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getMainCards(): ResponseBody
}