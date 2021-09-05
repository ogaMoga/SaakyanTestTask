package com.ogamoga.developerslive.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.ogamoga.developerslive.domain.model.SectionType

@Entity(
    tableName = "links", foreignKeys = [ForeignKey(
        entity = ItemsEntity::class,
        parentColumns = ["id"],
        childColumns = ["id"]
    )],
    primaryKeys = ["id", "type"]
)
data class LinksEntity(
    val id: Int,
    val type: SectionType,
    val nextId: Int?
)
