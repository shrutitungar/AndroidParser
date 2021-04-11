package com.sample.androidparser.data.model

import java.io.Serializable

data class Parser(
    val id: Int,
    val name: String,
    val tag: String,
    val color: String,
    val criteria: List<Criteria>
) : Serializable
