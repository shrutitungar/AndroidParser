package com.sample.androidparser.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sample.androidparser.data.repository.DataRepository
import com.sample.androidparser.utils.Resource
import kotlinx.coroutines.Dispatchers

class DataViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = dataRepository.getData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message?: "Error Occured"))
        }
    }
}