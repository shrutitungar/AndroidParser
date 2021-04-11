package com.sample.androidparser.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getData() = apiService.getData()
}