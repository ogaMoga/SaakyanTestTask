package com.ogamoga.developerslive.domain.model

data class Item(
    val id: Int,
    val url: String,
    val description: String,
    var hasPrevious: Boolean
)
