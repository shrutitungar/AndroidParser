package com.sample.androidparser.data.api

import com.sample.androidparser.data.model.Parser
import retrofit2.http.GET

interface ApiService {

    @GET("/data")
    suspend fun getData(): List<Parser>
}