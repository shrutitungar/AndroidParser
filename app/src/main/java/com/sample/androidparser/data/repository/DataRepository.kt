package com.sample.androidparser.data.repository

import com.sample.androidparser.data.api.ApiHelper

class DataRepository(private val apiHelper: ApiHelper) {

    suspend fun getData() = apiHelper.getData()
}