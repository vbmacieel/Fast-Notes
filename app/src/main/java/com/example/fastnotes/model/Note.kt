package com.example.fastnotes.model

import java.util.*

data class Note(
    val id: Long = Random().nextLong(),
    val title: String,
    val description: String
)
