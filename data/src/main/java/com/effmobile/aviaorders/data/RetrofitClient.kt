package com.effmobile.aviaorders.data

import com.effmobile.aviaorders.data.alltickets.service.AllTicketsService
import com.effmobile.aviaorders.data.main.service.MainApiService
import com.effmobile.aviaorders.data.search.service.SearchApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    fun provideMainApiService(): MainApiService {
        val client = buildMainClient()
        return buildRetrofitObject(client = client)
            .create(MainApiService::class.java)
    }

    fun provideSearchApiService(): SearchApiService {
        val client = buildMainClient()
        return buildRetrofitObject(client = client)
            .create(SearchApiService::class.java)
    }

    fun provideAllTicketsApiService(): AllTicketsService {
        val client = buildMainClient()
        return buildRetrofitObject(client = client)
            .create(AllTicketsService::class.java)
    }

    private fun buildMainClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun buildRetrofitObject(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        const val BASE_URL = "https://drive.google.com/"
    }
}