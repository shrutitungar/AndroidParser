package com.sample.androidparser.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Variable(
    val type: String,

    @SerializedName("study_type")
    val studyType: String,

    @SerializedName("parameter_name")
    val parameterName: String,

    @SerializedName("min_value")
    val minValue: Int,

    @SerializedName("max_value")
    val maxValue: Int,

    @SerializedName("default_value")
    val defaultValue: Int,

    @SerializedName("values")
    val values: List<Double> = arrayListOf()
) : Serializable