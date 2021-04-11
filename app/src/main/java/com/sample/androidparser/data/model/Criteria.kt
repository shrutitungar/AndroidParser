package com.sample.androidparser.data.model

import java.io.Serializable

data class Criteria(
    val type: String,
    val text: String,
    val variable: Map<String, Variable>
) : Serializable
