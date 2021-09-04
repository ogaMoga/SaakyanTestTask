package com.ogamoga.developerslive.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ogamoga.developerslive.domain.model.SectionType

@Entity(tableName = "sections")
data class SectionsEntity(
    @PrimaryKey val type: SectionType,
    val currentPage: Int,
    val pagesCount: Int
)
