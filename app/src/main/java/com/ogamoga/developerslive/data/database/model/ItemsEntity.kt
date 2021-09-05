package com.ogamoga.developerslive.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemsEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val description: String
)
